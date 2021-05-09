package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesEntity(
    var seriesId: String,
    var title: String,
    var description: String,
    var rating: Float,
    var genre: String,
    var episode: String,
    var imagePath: String
): Parcelable