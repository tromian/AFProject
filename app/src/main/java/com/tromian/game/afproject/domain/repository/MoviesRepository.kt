package com.tromian.game.afproject.domain.repository


import android.util.Log

import com.tromian.game.afproject.AppConstants
import com.tromian.game.afproject.domain.Resource
import com.tromian.game.afproject.domain.models.Actor
import com.tromian.game.afproject.domain.models.Genre
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.data.network.tmdbapi.ApiFactory
import com.tromian.game.afproject.data.network.tmdbapi.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MoviesRepository {

    var genres: List<Genre>? = null

    init {
        if (genres == null) {
            GlobalScope.launch(Dispatchers.IO) {
                genres = getGenres()
            }
        }
    }

    suspend fun getCasts(movieId: Int): List<Actor> {
        val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.getCredits(movieId))
        return when (result) {
            is Resource.Success -> result.data.actorList.map {
                Actor(
                        id = it.id,
                        name = it.name,
                        imageUrl = getProfilePictureUrl() + it.profilePath
                )
            }
            is Resource.Error -> {
                Log.d("MyLog", result.message)
                emptyList()
            }

        }
    }

    suspend fun getGenres(): List<Genre> {
        val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.getGenres())
        return when (result) {
            is Resource.Success -> result.data.genres!!.map {
                Genre(
                        id = it.id,
                        name = it.name.toString()
                )
            }
            is Resource.Error -> {
                Log.d("MyLog", result.message)
                emptyList()
            }

        }

    }

    suspend fun nowPlaying(): List<Movie> {
        val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.getNowPlaying())
        return when (result) {
            is Resource.Success -> result.data.movieList!!.map {

                Movie(
                        id = it.id,
                        title = it.title,
                        genres = it.genreIds?.let { it1 -> loadGenres(it1) },
                        imageUrl = getPosterUrl() + it.posterPath,
                        reviewCount = it.voteCount,
                        pgAge = it.adult?.let { it1 -> checkAdultContent(it1) },
                        rating = it.voteAverage?.toInt(),
                        storyLine = it.overview
                )

            }
            is Resource.Error -> {
                Log.d("MyLog", result.message)
                emptyList()
            }
        }
    }

    private fun getPosterUrl(): String {
        return AppConstants.IMAGES_BASE_URL + AppConstants.POSTER_SIZE
    }

    private fun getProfilePictureUrl(): String {
        return AppConstants.IMAGES_BASE_URL + AppConstants.PROFILE_SIZE
    }

    private fun checkAdultContent(adult: Boolean): Int {
        return if (adult) AppConstants.ADULT_CONTENT_AGE
        else AppConstants.NOT_ADULT_CONTENT_AGE
    }

    private fun loadGenres(genreIds: List<Int>): String {
        return if (genres != null) {
            var result = ""
            genreIds.forEach { id ->
                genres!!.forEach { genre ->
                    if (id == genre.id) {
                        result += genre.name + " "
                    }
                }
            }
            result
        } else return ""
    }


}