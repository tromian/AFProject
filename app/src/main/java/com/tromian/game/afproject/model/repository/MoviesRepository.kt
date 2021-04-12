package com.tromian.game.afproject.model.repository

import android.content.Context
import android.util.Log
import com.tromian.game.afproject.AppConstants
import com.tromian.game.afproject.model.Resource
import com.tromian.game.afproject.model.data.loadMovies
import com.tromian.game.afproject.model.models.Actor
import com.tromian.game.afproject.model.models.Movie
import com.tromian.game.afproject.model.tmdbapi.ApiFactory
import com.tromian.game.afproject.model.tmdbapi.ConfigurationResponse

class MoviesRepository : BaseRepository(){

    suspend fun getMoviesList(context: Context) : List<Movie>{
        return loadMovies(context)
    }

    suspend fun getCasts(movieId : Int) : List<Actor> {
        val result = safeApiResponse(ApiFactory.tmdbApi.getCredits(movieId))
        return when(result) {
            is Resource.Success -> result.data.cast.map {
                Actor(
                        id = it.id,
                        name = it.name,
                        imageUrl = AppConstants.POSTER_URL + AppConstants.PROFILE_SIZE + it.profile_path
                )
            }
            is Resource.Error -> {
                Log.d("MyLog",result.message.toString())
                emptyList()
            }

        }
    }

    suspend fun nowPlaying() : List<Movie> {
        Log.d(AppConstants.LOG,"start nowPlaying in repository")
        val result = safeApiResponse(ApiFactory.tmdbApi.getNowPlaying())
        return when(result) {
            is Resource.Success -> result.data.results!!.map {
                Log.d(AppConstants.LOG,"start mapping to Movie")
                Log.d(AppConstants.LOG,it.toString())
                Movie(
                        id = it.id,
                        title = it.title,
                        imageUrl = AppConstants.POSTER_URL + AppConstants.POSTER_SIZE +it.posterPath,
                        reviewCount = it.vote_count,
                        pgAge = if (it.adult == true) 16 else 13,
                        rating = it.voteAverage?.toInt(),
                        storyLine = it.overview
                )

            }
            is Resource.Error -> {
                Log.d("MyLog",result.message.toString())
                emptyList()
            }
            }
        }


//    suspend fun nowPlaying() = ApiFactory.tmdbApi.getNowPlaying()
//            .body()!!.results!!
//            .map {
//                Movie(
//                        id = it.id,
//                        title = it.title,
//                        imageUrl = "https://image.tmdb.org/t/p/w500/"+it.posterPath,
//                        reviewCount = it.vote_count,
//                        pgAge = if (it.adult) 16 else 13,
//                        rating = it.voteAverage.toInt(),
//                        storyLine = it.overview
//
//                )
//            }




}