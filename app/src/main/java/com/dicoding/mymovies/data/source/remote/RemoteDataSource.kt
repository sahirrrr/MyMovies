package com.dicoding.mymovies.data.source.remote

import android.util.Log
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.data.source.remote.network.ApiConfig
import com.dicoding.mymovies.data.source.remote.response.PopularFilmResponse
import com.dicoding.mymovies.data.source.remote.response.UpcomingFilmResponse
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

    interface LoadPopularFilmCallback {
        fun onPopularFilmReceived(filmResponse: List<PopularFilmEntity>)
    }

    interface LoadUpcomingFilmCallback {
        fun onUpcomingFilmReceived(filmResponse: List<UpcomingFilmEntity>)
    }
}