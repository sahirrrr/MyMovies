package com.dicoding.mymovies.data.source.remote.response

import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.google.gson.annotations.SerializedName

data class PopularFilmResponse(
		@field:SerializedName("results")
		val results: List<PopularFilmEntity>
)
