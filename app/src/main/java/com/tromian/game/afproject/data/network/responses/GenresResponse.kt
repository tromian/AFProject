package com.tromian.game.afproject.data.network.responses

import com.tromian.game.afproject.data.network.models.JsonGenre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
        @SerialName("genres")
    val genres: List<JsonGenre>? = null
)