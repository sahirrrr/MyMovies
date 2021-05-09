package com.dicoding.mymovies.ui.series

import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.SeriesEntity
import com.dicoding.mymovies.utils.DataMovies

class SeriesViewModel: ViewModel() {
    fun getSeries(): List<SeriesEntity> = DataMovies.generateSeries()
}