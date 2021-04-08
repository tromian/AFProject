package com.tromian.game.afproject.model.models


import java.io.Serializable

data class Movie(
        val id: Int? = null,
        val pgAge: Int?= null,
        val title: String?= null,
        val genreIds: List<Int>?= null,
        val runningTime: Int?= null,
        val reviewCount: Int?= null,
        val isLiked: Boolean?= null,
        val rating: Int?= null,
        val imageUrl: String?= null,
        val detailImageUrl: String?= null,
        val storyLine: String?= null,
        val actors: List<Actor>?= null,
) : Serializable
