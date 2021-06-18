package com.dicoding.mymovies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpcomingFilmResponse(
        @field:SerializedName("results")
        val results: List<UpcomingFilmResults>
)

data class UpcomingFilmResults(
        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("title")
        val title: String,

        @field:SerializedName("backdrop_path")
        val backdropPath: String,
)