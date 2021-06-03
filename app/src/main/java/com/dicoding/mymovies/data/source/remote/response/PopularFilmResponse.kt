package com.dicoding.mymovies.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularFilmResponse(
		@field:SerializedName("results")
		val results: List<PopularFilmResults>
)

data class PopularFilmResults(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,
)

