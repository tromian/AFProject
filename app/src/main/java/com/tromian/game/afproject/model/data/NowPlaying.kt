package com.tromian.game.afproject.model.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlaying(
    @SerializedName("dates")
        val dates: Dates,
    @SerializedName("page")
        val page: Int,
    @SerializedName("results")
        val results: List<JsonMovie>,
    @SerializedName("total_pages")
        val total_pages: Int,
    @SerializedName("total_results")
        val total_results: Int
)