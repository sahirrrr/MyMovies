package com.dicoding.mymovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.remote.response.DetailFilmResponse
import com.dicoding.mymovies.data.source.remote.response.DetailSeriesResponse

class DetailViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    private lateinit var moviesId: String
    private lateinit var tvId: String

    fun setSelectedFilm(moviesId: String) {
        this.moviesId = moviesId
    }

    fun setSelectedSeries(tvId: String) {
        this.tvId = tvId
    }

    fun getDetailFilm(): LiveData<DetailFilmResponse> = moviesRepository.getDetailFilm(moviesId.toInt())

    fun getDetailSeries(): LiveData<DetailSeriesResponse> = moviesRepository.getDetailSeries(tvId.toInt())
}