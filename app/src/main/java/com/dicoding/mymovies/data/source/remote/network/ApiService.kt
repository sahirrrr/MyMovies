package com.dicoding.mymovies.data.source.remote.network

import com.dicoding.mymovies.BuildConfig
import com.dicoding.mymovies.data.source.remote.response.PopularFilmResponse
import com.dicoding.mymovies.data.source.remote.response.UpcomingFilmResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Popular Movies
    @GET("movie/popular")
    fun getPopularFilm(
            @Query("api_key") api_key: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Call<PopularFilmResponse>

    // Upcoming Movies
    @GET("movie/upcoming")
    fun getUpcomingFilm(
            @Query("api_key") api_key: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Call<UpcomingFilmResponse>
}