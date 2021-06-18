package com.dicoding.mymovies.ui.mylist

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.core.domain.usecase.MoviesUseCase

class MyListViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {

    fun getFavoriteFilm() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getFavoriteFilm())

    fun getFavoriteSeries() = LiveDataReactiveStreams.fromPublisher(moviesUseCase.getFavoriteSeries())
}