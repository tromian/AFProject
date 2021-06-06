package com.tromian.game.afproject.domain.models

import java.io.Serializable

data class Actor(
        val id: Int,
        val name: String,
        val imageUrl: String? = null
) : Serializable