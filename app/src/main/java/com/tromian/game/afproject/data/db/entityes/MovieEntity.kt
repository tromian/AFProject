package com.tromian.game.afproject.data.db.entityes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val pgAge: Int? = null,
    val title: String,
    val genres: String? = null,
    val runningTime: Int? = null,
    val reviewCount: Int? = null,
    val isLiked: Boolean? = null,
    val rating: Int? = null,
    val imageUrl: String? = null,
    val detailImageUrl: String? = null,
    val storyLine: String
)
