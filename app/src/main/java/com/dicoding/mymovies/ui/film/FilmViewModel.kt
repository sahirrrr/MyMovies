package com.dicoding.mymovies.ui.film

import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.utils.DataMovies
import com.dicoding.mymovies.data.FilmEntity

class FilmViewModel: ViewModel() {
    fun getFilm(): List<FilmEntity> = DataMovies.generateFilm()
}