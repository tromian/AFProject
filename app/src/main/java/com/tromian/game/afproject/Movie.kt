package com.tromian.game.afproject


data class Movie(
        val name: String,
        val category: String,
        val age: String,
        val rating: Int = 0,
        val reviewers: Int,
        val storyline: String,
        val runtime: Int,
        val titleImage: Int,
) {
}