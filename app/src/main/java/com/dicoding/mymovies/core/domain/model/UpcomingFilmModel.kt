package com.dicoding.mymovies.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpcomingFilmModel(
    val id: Int,

    val title: String,

    val backdropPath: String,
) : Parcelable