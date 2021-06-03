package com.dicoding.mymovies.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.vo.Resource

class SeriesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    fun getPopularSeries(): LiveData<Resource<PagedList<PopularSeriesEntity>>> = moviesRepository.getPopularSeries()

    fun getTopRatedSeries(): LiveData<Resource<PagedList<TopRatedSeriesEntity>>> = moviesRepository.getTopRatedSeries()
}