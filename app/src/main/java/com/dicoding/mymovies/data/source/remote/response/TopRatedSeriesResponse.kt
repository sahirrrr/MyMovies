package com.dicoding.mymovies.data.source.remote.response

import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.google.gson.annotations.SerializedName

data class TopRatedSeriesResponse(
        @field:SerializedName("results")
        val results: List<TopRatedSeriesEntity>
)
