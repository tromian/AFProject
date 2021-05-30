package com.tromian.game.afproject.model.repository

import android.content.Context
import com.tromian.game.afproject.model.data.loadMovies
import com.tromian.game.afproject.model.models.Movie

class MoviesRepository {

    suspend fun getMoviesList(context: Context) : List<Movie>{
        return loadMovies(context)
    }

}