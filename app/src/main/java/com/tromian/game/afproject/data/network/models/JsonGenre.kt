package com.tromian.game.afproject.data.network.models

import com.google.gson.annotations.SerializedName

data class JsonGenre(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null
)