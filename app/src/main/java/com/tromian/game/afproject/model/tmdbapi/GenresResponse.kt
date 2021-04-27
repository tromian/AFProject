package com.tromian.game.afproject.model.tmdbapi

import com.tromian.game.afproject.model.data.JsonGenre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
        @SerialName("genres")
    val genres: List<JsonGenre>? = null
)