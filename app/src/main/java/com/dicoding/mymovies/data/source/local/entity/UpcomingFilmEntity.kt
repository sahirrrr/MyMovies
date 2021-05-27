package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "upcoming_film_table")
@Parcelize
data class UpcomingFilmEntity(
    @PrimaryKey
    @NonNull
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    var favorite :Boolean = false
): Parcelable