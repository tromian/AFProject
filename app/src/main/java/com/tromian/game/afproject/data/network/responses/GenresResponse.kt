package com.tromian.game.afproject.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tromian.game.afproject.data.network.models.JsonGenre

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<JsonGenre>? = null
)