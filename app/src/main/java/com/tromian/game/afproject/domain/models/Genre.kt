package com.tromian.game.afproject.domain.models

import java.io.Serializable

data class Genre(
        val id: Int,
        val name: String = ""
) : Serializable
