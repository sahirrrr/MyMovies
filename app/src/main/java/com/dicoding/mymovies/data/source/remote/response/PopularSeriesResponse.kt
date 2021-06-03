package com.dicoding.mymovies.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularSeriesResponse(
        @field:SerializedName("results")
        val results: List<PopularSeriesResults>,
)

data class PopularSeriesResults(
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
