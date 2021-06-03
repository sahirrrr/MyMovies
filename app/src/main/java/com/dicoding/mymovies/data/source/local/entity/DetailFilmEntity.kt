package com.dicoding.mymovies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_film_table")
data class DetailFilmEntity(
    @PrimaryKey
    val id: Int,

    val title: String,

    val originalLanguage: String,

    val releaseDate: String,

    val tagLine: String,

    val overview: String,

    val voteAverage: Double,

    val backdropPath: String,

    val posterPath: String,

    var favorite :Boolean = false
)
