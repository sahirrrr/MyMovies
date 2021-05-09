package com.dicoding.mymovies.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity

class FilmViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getFilm(): LiveData<List<PopularFilmEntity>> = moviesRepository.getPopularFilm()

    fun getUpcomingFilm(): LiveData<List<UpcomingFilmEntity>> = moviesRepository.getUpcomingFilm()
}