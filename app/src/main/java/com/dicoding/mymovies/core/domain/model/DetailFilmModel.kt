package com.dicoding.mymovies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailFilmModel(
    val id: Int,

    val title: String,

    val originalLanguage: String,

    val releaseDate: String,

    val tagLine: String,

    val overview: String,

    val voteAverage: Double,

    val backdropPath: String,

    val posterPath: String,

    var favorite :Boolean = false
) : Parcelable