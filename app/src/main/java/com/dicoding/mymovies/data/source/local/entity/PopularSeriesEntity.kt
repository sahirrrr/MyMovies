package com.dicoding.mymovies.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularSeriesEntity(
		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("first_air_date")
		val firstAirDate: String,

		@field:SerializedName("poster_path")
		val posterPath: String,

		@field:SerializedName("vote_average")
		val voteAverage: Double
): Parcelable
