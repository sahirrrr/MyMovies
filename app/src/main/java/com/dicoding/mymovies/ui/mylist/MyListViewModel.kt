package com.dicoding.mymovies.ui.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.DetailFilmEntity
import com.dicoding.mymovies.data.source.local.entity.DetailSeriesEntity

class MyListViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    fun getFavoriteFilm(): LiveData<PagedList<DetailFilmEntity>> = moviesRepository.getFavoriteFilm()

    fun getFavoriteSeries(): LiveData<PagedList<DetailSeriesEntity>> = moviesRepository.getFavoriteSeries()
}