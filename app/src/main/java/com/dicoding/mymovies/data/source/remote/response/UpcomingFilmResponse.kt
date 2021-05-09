package com.dicoding.mymovies.data.source.remote.response

import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.google.gson.annotations.SerializedName

data class UpcomingFilmResponse(

        @field:SerializedName("results")
        val results: List<UpcomingFilmEntity>

)