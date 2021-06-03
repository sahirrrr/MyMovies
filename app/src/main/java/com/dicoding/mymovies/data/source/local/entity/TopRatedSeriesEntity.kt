package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "top_rated_series_table")
data class TopRatedSeriesEntity (
        @PrimaryKey
        val id: Int,

        val name: String,

        val firstAirDate: String,

        val posterPath: String,

        val voteAverage: Double,

        var favorite :Boolean = false
): Parcelable