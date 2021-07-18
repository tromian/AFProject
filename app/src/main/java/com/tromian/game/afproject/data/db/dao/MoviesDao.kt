package com.tromian.game.afproject.data.db.dao

import androidx.room.*
import com.tromian.game.afproject.data.db.entityes.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM now_playing")
    suspend fun getNowPlaying() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

}