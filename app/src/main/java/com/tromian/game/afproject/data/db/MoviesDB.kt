package com.tromian.game.afproject.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tromian.game.afproject.data.db.dao.ActorsDao
import com.tromian.game.afproject.data.db.dao.MoviesDao
import com.tromian.game.afproject.data.db.entityes.ActorEntity
import com.tromian.game.afproject.data.db.entityes.MovieEntity

@Database(entities = [MovieEntity::class, ActorEntity::class], version = 1)
abstract class MoviesDB : RoomDatabase() {

    abstract fun movieDao(): MoviesDao
    abstract fun actorDao(): ActorsDao

    companion object {

        private const val DATABASE_NAME = "movies.db"

        val instance : MoviesDB by lazy {
            Room.databaseBuilder(
                Application().applicationContext,
                MoviesDB::class.java,
                DATABASE_NAME
            ).build()
        }
    }

}