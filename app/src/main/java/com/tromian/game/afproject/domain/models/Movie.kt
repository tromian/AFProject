package com.tromian.game.afproject.domain.models


import java.io.Serializable

data class Movie(
        val id: Int,
        val pgAge: Int?= null,
        val title: String,
        val genres: String?= null,
        val runningTime: Int?= null,
        val reviewCount: Int?= null,
        val isLiked: Boolean?= null,
        val rating: Int?= null,
        val imageUrl: String?= null,
        val detailImageUrl: String?= null,
        val storyLine: String
) : Serializable
