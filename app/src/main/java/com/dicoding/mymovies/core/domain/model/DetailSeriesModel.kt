package com.dicoding.mymovies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailSeriesModel(
    val id: Int,

    val name: String,

    val firstAirDate: String,

    val originalLanguage: String,

    val numberOfSeasons: Int,

    val numberOfEpisodes: Int,

    val voteAverage: Double,

    val overview: String,

    val posterPath: String,

    val backdropPath: String,

    var favorite :Boolean = false
) : Parcelable