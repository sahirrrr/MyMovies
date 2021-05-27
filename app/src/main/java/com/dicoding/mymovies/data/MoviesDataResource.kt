package com.dicoding.mymovies.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.data.source.remote.response.DetailFilmResponse
import com.dicoding.mymovies.data.source.remote.response.DetailSeriesResponse
import com.dicoding.mymovies.vo.Resource

interface MoviesDataResource {

    fun getPopularFilm(): LiveData<Resource<PagedList<PopularFilmEntity>>>

    fun getUpcomingFilm(): LiveData<Resource<PagedList<UpcomingFilmEntity>>>

    fun getPopularSeries(): LiveData<Resource<PagedList<PopularSeriesEntity>>>

    fun getTopRatedSeries(): LiveData<Resource<PagedList<TopRatedSeriesEntity>>>

    fun getDetailFilm(moviesId: Int): LiveData<DetailFilmResponse>

    fun getDetailSeries(tvId: Int): LiveData<DetailSeriesResponse>
}