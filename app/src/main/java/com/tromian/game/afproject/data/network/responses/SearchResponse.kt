package com.tromian.game.afproject.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tromian.game.afproject.data.network.models.JsonMovie

data class SearchResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<JsonMovie>? = null,
    @SerializedName("total_pages")
    val totalRages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)