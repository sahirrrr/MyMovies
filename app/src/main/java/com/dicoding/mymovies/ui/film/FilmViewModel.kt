package com.dicoding.mymovies.ui.film

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.core.domain.usecase.MoviesUseCase

class FilmViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {
    fun getPopularFilm() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getPopularFilm())

    fun getUpcomingFilm() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getUpcomingFilm())
}