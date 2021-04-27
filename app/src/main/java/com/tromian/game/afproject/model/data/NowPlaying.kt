package com.tromian.game.afproject.model.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlaying(
        @SerializedName("dates")
        val dates: Dates? = null,
        @SerializedName("page")
        val page: Int? = null,
        @SerializedName("results")
        val movieList: List<JsonMovie>? = null,
        @SerializedName("total_pages")
        val totalPages: Int? = null,
        @SerializedName("total_results")
        val totalResults: Int? = null
)