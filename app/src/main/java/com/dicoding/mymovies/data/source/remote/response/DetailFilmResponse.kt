package com.dicoding.mymovies.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailFilmResponse(
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("tagline")
	val tagLine: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("poster_path")
	val posterPath: String
)