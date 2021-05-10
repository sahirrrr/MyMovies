package com.dicoding.mymovies.data.source.remote.response

import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.google.gson.annotations.SerializedName

data class PopularSeriesResponse(
        @field:SerializedName("results")
        val results: List<PopularSeriesEntity>,
)
