package com.dicoding.mymovies.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mymovies.data.source.remote.network.ApiConfig
import com.dicoding.mymovies.data.source.remote.response.*
import com.dicoding.mymovies.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }
    }

    fun getPopularFilm(): LiveData<ApiResponse<List<PopularFilmResults>>> {
        EspressoIdlingResources.increment()
        val responseResults = MutableLiveData<ApiResponse<List<PopularFilmResults>>>()
        val client = ApiConfig.getApiService().getPopularFilm()
        client.enqueue(object : Callback<PopularFilmResponse> {
            override fun onResponse(call: Call<PopularFilmResponse>, response: Response<PopularFilmResponse>) {
                responseResults.value = response.body()?.results?.let { ApiResponse.success(it) }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<PopularFilmResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
        return responseResults
    }

    fun getUpcomingFilm(): LiveData<ApiResponse<List<UpcomingFilmResults>>> {
        EspressoIdlingResources.increment()
        val responseResults = MutableLiveData<ApiResponse<List<UpcomingFilmResults>>>()
        val client = ApiConfig.getApiService().getUpcomingFilm()
        client.enqueue(object : Callback<UpcomingFilmResponse> {
            override fun onResponse(call: Call<UpcomingFilmResponse>, response: Response<UpcomingFilmResponse>) {
                responseResults.value = response.body()?.results?.let { ApiResponse.success(it) }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<UpcomingFilmResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
        return responseResults
    }

    fun getTopRatedSeries(): LiveData<ApiResponse<List<TopRatedSeriesResults>>> {
        EspressoIdlingResources.increment()
        val responseResults = MutableLiveData<ApiResponse<List<TopRatedSeriesResults>>>()
        val client = ApiConfig.getApiService().getTopRatedSeries()
        client.enqueue(object : Callback<TopRatedSeriesResponse> {
            override fun onResponse(call: Call<TopRatedSeriesResponse>, response: Response<TopRatedSeriesResponse>) {
                responseResults.value = response.body()?.results?.let { ApiResponse.success(it) }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TopRatedSeriesResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
        return responseResults
    }

    fun getPopularSeries(): LiveData<ApiResponse<List<PopularSeriesResults>>> {
        EspressoIdlingResources.increment()
        val responseResults = MutableLiveData<ApiResponse<List<PopularSeriesResults>>>()
        val client = ApiConfig.getApiService().getPopularSeries()
        client.enqueue(object : Callback<PopularSeriesResponse> {
            override fun onResponse(call: Call<PopularSeriesResponse>, response: Response<PopularSeriesResponse>) {
                responseResults.value = response.body()?.results?.let { ApiResponse.success(it) }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<PopularSeriesResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
        return responseResults
    }

    fun getDetailFilm(filmId: Int): LiveData<ApiResponse<DetailFilmResponse>> {
        EspressoIdlingResources.increment()
        val responseResults = MutableLiveData<ApiResponse<DetailFilmResponse>>()
        val client = ApiConfig.getApiService().getDetailFilm(filmId)
        client.enqueue(object : Callback<DetailFilmResponse> {
            override fun onResponse(call: Call<DetailFilmResponse>, response: Response<DetailFilmResponse>) {
                responseResults.value = ApiResponse.success(response.body() as DetailFilmResponse)
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailFilmResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
        return responseResults
    }

    fun getDetailSeries(tvId: Int): LiveData<ApiResponse<DetailSeriesResponse>> {
        EspressoIdlingResources.increment()
        val responseResults = MutableLiveData<ApiResponse<DetailSeriesResponse>>()
        val client = ApiConfig.getApiService().getDetailSeries(tvId)
        client.enqueue(object : Callback<DetailSeriesResponse> {
            override fun onResponse(call: Call<DetailSeriesResponse>, response: Response<DetailSeriesResponse>) {
                responseResults.value = ApiResponse.success(response.body() as DetailSeriesResponse)
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailSeriesResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()

            }
        })
        return responseResults
    }
}