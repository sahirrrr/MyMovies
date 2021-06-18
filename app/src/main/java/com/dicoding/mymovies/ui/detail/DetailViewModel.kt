package com.dicoding.mymovies.ui.detail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.core.domain.model.DetailFilmModel
import com.dicoding.mymovies.core.domain.model.DetailSeriesModel
import com.dicoding.mymovies.core.domain.usecase.MoviesUseCase

class DetailViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {

    fun getDetailFilm(filmId: Int) = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getDetailFilm(filmId))

    fun getDetailSeries(tvId: Int) = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getDetailSeries(tvId))

    fun setFavoriteFilm(detailFilmModel: DetailFilmModel, state: Boolean) {
        moviesUseCase.insertFavoriteFilm(detailFilmModel, state)
    }

    fun setFavoriteSeries(detailSeriesModel: DetailSeriesModel, state: Boolean) {
        moviesUseCase.insertFavoriteSeries(detailSeriesModel, state)
    }

}