package com.tromian.game.afproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tromian.game.afproject.data.db.entityes.MovieEntity

@Dao
interface MoviesDao {


    @Query("SELECT * FROM now_playing")
    suspend fun getNowPlaying() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)



}