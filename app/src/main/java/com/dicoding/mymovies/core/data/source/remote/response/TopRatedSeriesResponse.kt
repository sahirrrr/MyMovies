package com.dicoding.mymovies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TopRatedSeriesResponse(
        @field:SerializedName("results")
        val results: List<TopRatedSeriesResults>
)

data class TopRatedSeriesResults(
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
)