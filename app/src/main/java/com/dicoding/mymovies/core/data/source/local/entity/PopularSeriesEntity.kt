package com.dicoding.mymovies.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "popular_series_table")
data class PopularSeriesEntity(
		@PrimaryKey
		val id: Int,

		val name: String,

		val firstAirDate: String,

		val posterPath: String,

		val voteAverage: Double,

		var favorite :Boolean = false
): Parcelable
