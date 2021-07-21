package com.tromian.game.afproject.data.db.dao

import androidx.room.*
import com.tromian.game.afproject.data.db.entityes.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies ")
    suspend fun getNowPlaying() : List<MovieEntity>

    @Query("SELECT * FROM movies WHERE liked = 1 ")
    suspend fun getFavourite() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

}