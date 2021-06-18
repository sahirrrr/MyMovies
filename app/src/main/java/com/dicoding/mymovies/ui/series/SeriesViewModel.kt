package com.dicoding.mymovies.ui.series

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.core.domain.usecase.MoviesUseCase

class SeriesViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {
    fun getPopularSeries() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getPopularSeries())

    fun getTopRatedSeries() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getTopRatedSeries())
}