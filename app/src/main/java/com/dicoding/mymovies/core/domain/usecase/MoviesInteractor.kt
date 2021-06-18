package com.dicoding.mymovies.core.domain.usecase

import androidx.paging.PagedList
import com.dicoding.mymovies.core.domain.IMoviesRepository
import com.dicoding.mymovies.core.domain.model.*
import com.dicoding.mymovies.vo.Resource
import io.reactivex.Flowable

class MoviesInteractor(private val moviesRepository: IMoviesRepository): MoviesUseCase {
    override fun getPopularFilm(): Flowable<Resource<PagedList<PopularFilmModel>>> =
        moviesRepository.getPopularFilm()

    override fun getUpcomingFilm(): Flowable<Resource<PagedList<UpcomingFilmModel>>> =
        moviesRepository.getUpcomingFilm()

    override fun getPopularSeries(): Flowable<Resource<PagedList<PopularSeriesModel>>> =
        moviesRepository.getPopularSeries()

    override fun getTopRatedSeries(): Flowable<Resource<PagedList<TopRatedSeriesModel>>> =
        moviesRepository.getTopRatedSeries()

    override fun getDetailFilm(filmId: Int): Flowable<Resource<DetailFilmModel>> =
        moviesRepository.getDetailFilm(filmId)

    override fun getDetailSeries(tvId: Int): Flowable<Resource<DetailSeriesModel>> =
        moviesRepository.getDetailSeries(tvId)

    override fun getFavoriteFilm(): Flowable<PagedList<DetailFilmModel>> =
        moviesRepository.getFavoriteFilm()

    override fun insertFavoriteFilm(detailFilm: DetailFilmModel, isFavorite: Boolean) {
        moviesRepository.insertFavoriteFilm(detailFilm, isFavorite)
    }

    override fun getFavoriteSeries(): Flowable<PagedList<DetailSeriesModel>> =
        moviesRepository.getFavoriteSeries()

    override fun insertFavoriteSeries(detailSeries: DetailSeriesModel, isFavorite: Boolean) {
        moviesRepository.insertFavoriteSeries(detailSeries, isFavorite)
    }

}