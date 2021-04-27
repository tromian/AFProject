package com.tromian.game.afproject.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonGenre(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null
)