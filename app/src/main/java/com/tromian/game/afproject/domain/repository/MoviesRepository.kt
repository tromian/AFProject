package com.tromian.game.afproject.domain.repository

import com.tromian.game.afproject.domain.models.Actor
import com.tromian.game.afproject.domain.models.Genre
import com.tromian.game.afproject.domain.models.Movie

interface MoviesRepository {
    suspend fun getCasts(movieId: Int): List<Actor>
    suspend fun getGenres(): List<Genre>
    suspend fun nowPlaying(page : Int): List<Movie>
    suspend fun saveMovieList(movies: List<Movie>)
    suspend fun getSavedMovieList() : List<Movie>
}