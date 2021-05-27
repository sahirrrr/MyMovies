package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "popular_film_table")
@Parcelize
data class PopularFilmEntity(
        @PrimaryKey
        @NonNull
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

        var favorite :Boolean = false
): Parcelable