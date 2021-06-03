package com.dicoding.mymovies.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.mymovies.data.source.local.entity.*
import com.dicoding.mymovies.vo.Resource

interface MoviesDataResource {

    fun getPopularFilm(): LiveData<Resource<PagedList<PopularFilmEntity>>>

    fun getUpcomingFilm(): LiveData<Resource<PagedList<UpcomingFilmEntity>>>

    fun getPopularSeries(): LiveData<Resource<PagedList<PopularSeriesEntity>>>

    fun getTopRatedSeries(): LiveData<Resource<PagedList<TopRatedSeriesEntity>>>

    fun getDetailFilm(filmId: Int): LiveData<Resource<DetailFilmEntity>>

    fun getDetailSeries(tvId: Int): LiveData<Resource<DetailSeriesEntity>>


    fun getFavoriteFilm(): LiveData<PagedList<DetailFilmEntity>>

    fun insertFavoriteFilm(detailFilm: DetailFilmEntity, isFavorite: Boolean)

    fun getFavoriteSeries(): LiveData<PagedList<DetailSeriesEntity>>

    fun insertFavoriteSeries(detailSeries: DetailSeriesEntity, isFavorite: Boolean)
}