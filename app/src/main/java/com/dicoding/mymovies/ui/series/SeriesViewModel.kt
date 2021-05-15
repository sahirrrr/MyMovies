package com.dicoding.mymovies.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity

class SeriesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getPopularSeries(): LiveData<List<PopularSeriesEntity>> = moviesRepository.getPopularSeries()

    fun getTopRatedSeries(): LiveData<List<TopRatedSeriesEntity>> = moviesRepository.getTopRatedSeries()
}