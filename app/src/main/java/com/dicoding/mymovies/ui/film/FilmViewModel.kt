package com.dicoding.mymovies.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.vo.Resource

class FilmViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getPopularFilm(): LiveData<Resource<PagedList<PopularFilmEntity>>> = moviesRepository.getPopularFilm()

    fun getUpcomingFilm(): LiveData<Resource<PagedList<UpcomingFilmEntity>>> = moviesRepository.getUpcomingFilm()
}