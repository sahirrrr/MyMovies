package com.dicoding.mymovies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmEntity(
        var movieId: String,
        var title: String,
        var description: String,
        var rating: Float,
        var genre: String,
        var duration: String,
        var imagePath: String
): Parcelable