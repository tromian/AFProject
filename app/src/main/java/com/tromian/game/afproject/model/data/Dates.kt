package com.tromian.game.afproject.model.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Dates(
        @SerializedName("maximum")
    val maximum: String,
        @SerializedName("minimum")
    val minimum: String
)