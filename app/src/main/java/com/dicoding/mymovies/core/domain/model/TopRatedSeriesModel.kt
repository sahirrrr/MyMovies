package com.dicoding.mymovies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopRatedSeriesModel(
    val id: Int,

    val name: String,

    val firstAirDate: String,

    val posterPath: String,

    val voteAverage: Double,

    var favorite :Boolean = false
) : Parcelable