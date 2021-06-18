package com.dicoding.mymovies.core.data

import com.dicoding.mymovies.core.data.source.remote.ApiResponse
import com.dicoding.mymovies.core.utils.AppExecutors
import com.dicoding.mymovies.vo.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@Suppress("LeakingThis")
abstract class NetworkBoundResource<RequestType,ResultType>(private val mExecutors: AppExecutors) {

    private val result = PublishSubject.create<Resource<ResultType>>()
    private val compositeDisposable = CompositeDisposable()

    init {

        val dbSource = loadFromDB()
        val db = dbSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe { dbValue ->
                dbSource.unsubscribeOn(Schedulers.io())
                if (shouldFetch(dbValue)) {
                    fetchFromNetwork()
                } else {
                    result.onNext(Resource.success(dbValue))
                }
            }
        compositeDisposable.add(db)
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flowable<ResultType>

    protected abstract fun shouldFetch(data : ResultType?): Boolean

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(response: RequestType)

    private fun fetchFromNetwork(){
        val apiResponse = createCall()

        result.onNext(Resource.loading(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe {
                    responseResult ->
                when(responseResult){
                    is ApiResponse.Success -> {
                        saveCallResult(responseResult.data)
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(Resource.success(it))
                            }
                    }
                    is ApiResponse.Empty -> {
                        val dbSource = loadFromDB()
                        dbSource.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .take(1)
                            .subscribe {
                                dbSource.unsubscribeOn(Schedulers.io())
                                result.onNext(Resource.success(it))
                            }
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        result.onNext(Resource.error(responseResult.errorMessage,null))
                    }
                }
            }
        compositeDisposable.add(response)
    }

    fun asFlowable() : Flowable<Resource<ResultType>> = result.toFlowable(BackpressureStrategy.BUFFER)
}