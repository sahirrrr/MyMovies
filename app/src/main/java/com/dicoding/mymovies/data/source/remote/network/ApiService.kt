package com.dicoding.mymovies.data.source.remote.network

import com.dicoding.mymovies.BuildConfig
import com.dicoding.mymovies.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Popular Movies
    @GET("movie/popular")
    fun getPopularFilm(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Call<PopularFilmResponse>

    // Upcoming Movies
    @GET("movie/upcoming")
    fun getUpcomingFilm(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Call<UpcomingFilmResponse>

    // Popular Tv Series
    @GET("tv/popular")
    fun getPopularSeries(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Call<PopularSeriesResponse>

    // Top Rated Tv Series
    @GET("tv/top_rated")
    fun getTopRatedSeries(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US",
            @Query("page") page: Int = 2
    ): Call<TopRatedSeriesResponse>

    // Detail Movies
    @GET("movie/{movie_id}")
    fun getDetailFilm(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Call<DetailFilmResponse>

    @GET("tv/{tv_id}")
    fun getDetailSeries(
            @Path("tv_id") tvId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US",
    ): Call<DetailSeriesResponse>
}