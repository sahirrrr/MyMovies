package com.dicoding.mymovies.data.source

import androidx.lifecycle.LiveData
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.data.source.remote.response.DetailFilmResponse
import com.dicoding.mymovies.data.source.remote.response.DetailSeriesResponse

interface MoviesDataResource {

    fun getPopularFilm(): LiveData<List<PopularFilmEntity>>

    fun getUpcomingFilm(): LiveData<List<UpcomingFilmEntity>>

    fun getPopularSeries(): LiveData<List<PopularSeriesEntity>>

    fun getTopRatedSeries(): LiveData<List<TopRatedSeriesEntity>>

    fun getDetailFilm(moviesId: Int): LiveData<DetailFilmResponse>

    fun getDetailSeries(tvId: Int): LiveData<DetailSeriesResponse>
}