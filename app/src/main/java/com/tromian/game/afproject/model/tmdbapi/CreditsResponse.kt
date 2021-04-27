package com.tromian.game.afproject.model.tmdbapi

import com.google.gson.annotations.SerializedName
import com.tromian.game.afproject.model.data.JsonActor
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
        @SerializedName("cast")
    val actorList: List<JsonActor>,
        @SerializedName("id")
    val id: Int
)