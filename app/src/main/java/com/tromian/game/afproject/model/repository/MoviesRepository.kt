package com.tromian.game.afproject.model.repository

import android.content.Context
import com.tromian.game.afproject.model.data.loadMovies
import com.tromian.game.afproject.model.models.Movie
import com.tromian.game.afproject.model.tmdbapi.ApiFactory
import com.tromian.game.afproject.model.tmdbapi.TmdbAPI

class MoviesRepository : BaseRepository(){

    suspend fun getMoviesList(context: Context) : List<Movie>{
        return loadMovies(context)
    }

    fun nowPlaying() = ApiFactory.tmdbApi.getNowPlaying()





}