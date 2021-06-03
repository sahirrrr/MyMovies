package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "popular_film_table")
data class PopularFilmEntity(
        @PrimaryKey
        val id: Int,

        val title: String,

        val releaseDate: String,

        val posterPath: String,

        val voteAverage: Double,

        var favorite :Boolean = false
): Parcelable