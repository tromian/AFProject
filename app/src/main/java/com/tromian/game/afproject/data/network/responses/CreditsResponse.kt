package com.tromian.game.afproject.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tromian.game.afproject.data.network.models.JsonActor
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerializedName("cast")
    val actorList: List<JsonActor>? = null,
    @SerializedName("id")
    val id: Int? = null
)