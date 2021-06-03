package com.dicoding.mymovies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_series_table")
data class DetailSeriesEntity(
    @PrimaryKey
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
)
