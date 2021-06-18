package com.dicoding.mymovies.core.data.source.remote.network

import com.dicoding.mymovies.BuildConfig
import com.dicoding.mymovies.core.data.source.remote.response.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Popular Movies
    @GET("movie/popular")
    fun getPopularFilm(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Flowable<PopularFilmResponse>

    // Upcoming Movies
    @GET("movie/upcoming")
    fun getUpcomingFilm(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Flowable<UpcomingFilmResponse>

    // Popular Tv Series
    @GET("tv/popular")
    fun getPopularSeries(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Flowable<PopularSeriesResponse>

    // Top Rated Tv Series
    @GET("tv/top_rated")
    fun getTopRatedSeries(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US",
            @Query("page") page: Int = 2
    ): Flowable<TopRatedSeriesResponse>

    // Detail Movies
    @GET("movie/{movie_id}")
    fun getDetailFilm(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US"
    ): Flowable<DetailFilmResponse>

    @GET("tv/{tv_id}")
    fun getDetailSeries(
            @Path("tv_id") tvId: Int,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("language") language: String = "en-US",
    ): Flowable<DetailSeriesResponse>
}