package com.tromian.game.afproject.data.db.entityes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActorEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String? = null
)