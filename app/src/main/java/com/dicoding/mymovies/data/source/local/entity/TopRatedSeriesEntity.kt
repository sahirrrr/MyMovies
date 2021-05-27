package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "top_rated_series_table")
@Parcelize
data class TopRatedSeriesEntity (
        @PrimaryKey
        @NonNull
        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("first_air_date")
        val firstAirDate: String,

        @field:SerializedName("poster_path")
        val posterPath: String,

        @field:SerializedName("vote_average")
        val voteAverage: Double,

        var favorite :Boolean = false
): Parcelable