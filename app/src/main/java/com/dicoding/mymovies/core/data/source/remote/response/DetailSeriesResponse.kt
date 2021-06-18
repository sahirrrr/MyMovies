package com.dicoding.mymovies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailSeriesResponse(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int,

	@field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,
)
