package com.dicoding.mymovies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularFilmModel(
    val id: Int,

    val title: String,

    val releaseDate: String,

    val posterPath: String,

    val voteAverage: Double,

    var favorite :Boolean = false
) : Parcelable