package com.tromian.game.afproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tromian.game.afproject.data.db.entityes.GenreEntity

@Dao
interface GenresDao {

    @Query("SELECT * FROM genres")
    suspend fun getGenreList() : List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListGenre(genreList: List<GenreEntity>)

}