package com.tromian.game.afproject.domain.repository

import com.tromian.game.afproject.domain.models.Actor
import com.tromian.game.afproject.domain.models.Genre
import com.tromian.game.afproject.domain.models.Movie

interface MoviesRepository {
    suspend fun getMovieCastsByIdInApi(movieId: Int): List<Actor>
    suspend fun getAllGenreListFromApi(): List<Genre>
    suspend fun nowPlayingMoviesFromApiWithPage(page : Int): List<Movie>
    suspend fun saveMovieListToDB(movies: List<Movie>)
    suspend fun getSavedMovieListFromDB() : List<Movie>
    suspend fun saveMovieToDB(movie: Movie)
    suspend fun deleteMovieFromDB(movie: Movie)
    suspend fun searchMoviesByTitleInApi(title : String) : List<Movie>
    suspend fun getFavouriteMovieListFromDB() : List<Movie>
}