package com.dicoding.mymovies.core.domain.usecase

import androidx.paging.PagedList
import com.dicoding.mymovies.core.domain.model.*
import com.dicoding.mymovies.vo.Resource
import io.reactivex.Flowable

interface MoviesUseCase {
    fun getPopularFilm(): Flowable<Resource<PagedList<PopularFilmModel>>>

    fun getUpcomingFilm(): Flowable<Resource<PagedList<UpcomingFilmModel>>>

    fun getPopularSeries(): Flowable<Resource<PagedList<PopularSeriesModel>>>

    fun getTopRatedSeries(): Flowable<Resource<PagedList<TopRatedSeriesModel>>>

    fun getDetailFilm(filmId: Int): Flowable<Resource<DetailFilmModel>>

    fun getDetailSeries(tvId: Int): Flowable<Resource<DetailSeriesModel>>


    fun getFavoriteFilm(): Flowable<PagedList<DetailFilmModel>>

    fun insertFavoriteFilm(detailFilm: DetailFilmModel, isFavorite: Boolean)

    fun getFavoriteSeries(): Flowable<PagedList<DetailSeriesModel>>

    fun insertFavoriteSeries(detailSeries: DetailSeriesModel, isFavorite: Boolean)
}