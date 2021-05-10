package com.dicoding.mymovies.data.source.remote

import android.util.Log
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.data.source.remote.network.ApiConfig
import com.dicoding.mymovies.data.source.remote.response.*
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

    fun getPopularFilm(callback: LoadPopularFilmCallback) {
        val client = ApiConfig.getApiService().getPopularFilm()
        client.enqueue(object : Callback<PopularFilmResponse> {
            override fun onResponse(call: Call<PopularFilmResponse>, response: Response<PopularFilmResponse>) {
                callback.onPopularFilmReceived(response.body()?.results as List<PopularFilmEntity>)
            }

            override fun onFailure(call: Call<PopularFilmResponse>, t: Throwable) {
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
    }

    fun getUpcomingFilm(callback: LoadUpcomingFilmCallback) {
        val client = ApiConfig.getApiService().getUpcomingFilm()
        client.enqueue(object : Callback<UpcomingFilmResponse> {
            override fun onResponse(call: Call<UpcomingFilmResponse>, response: Response<UpcomingFilmResponse>) {
                callback.onUpcomingFilmReceived(response.body()?.results as List<UpcomingFilmEntity>)
            }

            override fun onFailure(call: Call<UpcomingFilmResponse>, t: Throwable) {
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
    }

    fun getTopRatedSeries(callback: LoadTopRatedSeriesCallback) {
        val client = ApiConfig.getApiService().getTopRatedSeries()
        client.enqueue(object : Callback<TopRatedSeriesResponse> {
            override fun onResponse(call: Call<TopRatedSeriesResponse>, response: Response<TopRatedSeriesResponse>) {
                callback.onTopRatedSeriesReceived(response.body()?.results as List<TopRatedSeriesEntity>)
            }

            override fun onFailure(call: Call<TopRatedSeriesResponse>, t: Throwable) {
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
    }

    fun getPopularSeries(callback: LoadPopularSeriesCallback) {
        val client = ApiConfig.getApiService().getPopularSeries()
        client.enqueue(object : Callback<PopularSeriesResponse> {
            override fun onResponse(call: Call<PopularSeriesResponse>, response: Response<PopularSeriesResponse>) {
                callback.onPopularSeriesReceived(response.body()?.results as List<PopularSeriesEntity>)
            }

            override fun onFailure(call: Call<PopularSeriesResponse>, t: Throwable) {
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }
        })
    }

    fun getDetailFilm(callback: LoadDetailFilmCallback, moviesId: Int) {
        val client = ApiConfig.getApiService().getDetailFilm(moviesId)
        client.enqueue(object : Callback<DetailFilmResponse> {
            override fun onResponse(call: Call<DetailFilmResponse>, response: Response<DetailFilmResponse>) {
                callback.onDetailFilmReceived(response.body() as DetailFilmResponse)
            }

            override fun onFailure(call: Call<DetailFilmResponse>, t: Throwable) {
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }

        })
    }

    fun getDetailSeries(callback: LoadDetailSeriesCallback, tvId: Int) {
        val client = ApiConfig.getApiService().getDetailSeries(tvId)
        client.enqueue(object : Callback<DetailSeriesResponse> {
            override fun onResponse(call: Call<DetailSeriesResponse>, response: Response<DetailSeriesResponse>) {
                callback.onDetailSeriesReceived(response.body() as DetailSeriesResponse)
            }

            override fun onFailure(call: Call<DetailSeriesResponse>, t: Throwable) {
                Log.d("RemoteDataSource", t.message.toString())
                t.printStackTrace()
            }

        })

    }

    interface LoadPopularFilmCallback {
        fun onPopularFilmReceived(filmResponse: List<PopularFilmEntity>)
    }

    interface LoadUpcomingFilmCallback {
        fun onUpcomingFilmReceived(filmResponse: List<UpcomingFilmEntity>)
    }

    interface LoadTopRatedSeriesCallback {
        fun onTopRatedSeriesReceived(seriesResponse: List<TopRatedSeriesEntity>)
    }

    interface LoadPopularSeriesCallback {
        fun onPopularSeriesReceived(seriesResponse: List<PopularSeriesEntity>)
    }

    interface LoadDetailFilmCallback {
        fun onDetailFilmReceived(filmResponse: DetailFilmResponse)
    }

    interface LoadDetailSeriesCallback {
        fun onDetailSeriesReceived(seriesResponse: DetailSeriesResponse)
    }

}