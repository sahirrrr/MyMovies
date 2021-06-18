package com.dicoding.mymovies.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "upcoming_film_table")
data class UpcomingFilmEntity(
    @PrimaryKey
    val id: Int,

    val title: String,

    val backdropPath: String,
): Parcelable