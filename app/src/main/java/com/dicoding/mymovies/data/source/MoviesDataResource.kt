package com.dicoding.mymovies.data.source

import androidx.lifecycle.LiveData
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity

interface MoviesDataResource {

    fun getPopularFilm(): LiveData<List<PopularFilmEntity>>

    fun getUpcomingFilm(): LiveData<List<UpcomingFilmEntity>>
}