package com.dicoding.mymovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.DetailFilmEntity
import com.dicoding.mymovies.data.source.local.entity.DetailSeriesEntity
import com.dicoding.mymovies.vo.Resource

class DetailViewModel(private val moviesRepository: MoviesRepository): ViewModel() {
    private var filmId = MutableLiveData<Int>()
    private var tvId = MutableLiveData<Int>()

    fun setSelectedFilm(filmId: Int) {
        this.filmId.value = filmId
    }

    fun setSelectedSeries(tvId: Int) {
        this.tvId.value = tvId
    }

    var detailFilm: LiveData<Resource<DetailFilmEntity>> =
        Transformations.switchMap(filmId) { mFilmId ->
            moviesRepository.getDetailFilm(mFilmId)
        }

    var detailSeries: LiveData<Resource<DetailSeriesEntity>> =
        Transformations.switchMap(tvId) { mTvId ->
            moviesRepository.getDetailSeries(mTvId)
        }

    fun setFavoriteFilm() {
        val detailFilmResource = detailFilm.value
        if (detailFilmResource != null) {
            val filmData = detailFilmResource.data
            if (filmData != null) {
                val detailFilmEntity = detailFilmResource.data
                val favorite = !filmData.favorite
                moviesRepository.insertFavoriteFilm(detailFilmEntity, favorite)
            }
        }
    }

    fun setFavoriteSeries() {
        val detailSeriesResource = detailSeries.value
        if (detailSeriesResource != null) {
            val seriesData = detailSeriesResource.data
            if (seriesData != null) {
                val detailSeriesEntity = detailSeriesResource.data
                val favorite = !seriesData.favorite
                moviesRepository.insertFavoriteSeries(detailSeriesEntity, favorite)
            }
        }
    }
}