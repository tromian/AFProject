package com.tromian.game.afproject.model.models

import java.io.Serializable

data class Actor(
        val id: Int? = null,
        val name: String? = null,
        val imageUrl: String? = null,
) : Serializable