package com.dicoding.mymovies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.mymovies.data.source.remote.ApiResponse
import com.dicoding.mymovies.data.source.remote.StatusResponse
import com.dicoding.mymovies.utils.AppExecutors
import com.dicoding.mymovies.vo.Resource

abstract class NetworkBoundResource<RequestType,ResultType>(private val mExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data : ResultType?): Boolean

    protected abstract fun saveCallResult(response: RequestType)

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    private fun fetchFromNetwork(dataSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dataSource) { newData ->
            result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dataSource)
            when (response.status) { StatusResponse.SUCCESS ->
                mExecutors.diskIO().execute{
                    saveCallResult(response.body)
                    mExecutors.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = Resource.success(newData)
                        }
                    }
                }
                StatusResponse.EMPTY -> mExecutors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData() : LiveData<Resource<ResultType>> = result

}