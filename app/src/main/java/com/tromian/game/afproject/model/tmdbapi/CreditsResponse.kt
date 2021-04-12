package com.tromian.game.afproject.model.tmdbapi

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
        @SerializedName("cast")
    val cast: List<Cast>,
        @SerializedName("id")
    val id: Int
)