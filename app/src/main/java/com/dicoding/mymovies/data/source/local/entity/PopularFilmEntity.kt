package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularFilmEntity(

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("title")
        val title: String,

        @field:SerializedName("release_date")
        val releaseDate: String,

        @field:SerializedName("poster_path")
        val posterPath: String,

        @field:SerializedName("vote_average")
        val voteAverage: Double,

): Parcelable