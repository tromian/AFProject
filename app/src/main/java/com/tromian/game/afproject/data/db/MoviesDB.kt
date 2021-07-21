package com.tromian.game.afproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tromian.game.afproject.data.db.dao.ActorsDao
import com.tromian.game.afproject.data.db.dao.GenresDao
import com.tromian.game.afproject.data.db.dao.MoviesDao
import com.tromian.game.afproject.data.db.entityes.ActorEntity
import com.tromian.game.afproject.data.db.entityes.GenreEntity
import com.tromian.game.afproject.data.db.entityes.MovieEntity

@Database(
    entities = [MovieEntity::class,
        ActorEntity::class,
        GenreEntity::class],
    version = 3
)
abstract class MoviesDB : RoomDatabase() {

    abstract fun movieDao(): MoviesDao
    abstract fun actorDao(): ActorsDao
    abstract fun genreDao(): GenresDao

    companion object {
        private const val DATABASE_NAME = "movies.db"

        private var INSTANCE: MoviesDB? = null

        private val lock = Any()

        fun getInstance(appContext: Context): MoviesDB {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        appContext,
                        MoviesDB::class.java, DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE!!
            }
        }

    }


}