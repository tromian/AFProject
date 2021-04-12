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
        val results: List<JsonMovie>? = null,
    @SerializedName("total_pages")
        val total_pages: Int? = null,
    @SerializedName("total_results")
        val total_results: Int? = null
)