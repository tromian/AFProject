package com.tromian.game.afproject.data.repository


import android.content.Context
import android.util.Log

import com.tromian.game.afproject.AppConstants
import com.tromian.game.afproject.MoviesApp
import com.tromian.game.afproject.data.db.MoviesDB
import com.tromian.game.afproject.data.db.entityes.MovieEntity
import com.tromian.game.afproject.data.network.models.JsonActor
import com.tromian.game.afproject.data.network.models.JsonGenre
import com.tromian.game.afproject.data.network.models.JsonMovie
import com.tromian.game.afproject.domain.Resource
import com.tromian.game.afproject.domain.models.Actor
import com.tromian.game.afproject.domain.models.Genre
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.data.network.tmdbapi.ApiFactory
import com.tromian.game.afproject.data.network.tmdbapi.ResponseWrapper
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MoviesDataRepository(val context: Context) : MoviesRepository {

    var genres: List<Genre>? = null
    val db = MoviesDB.getInstance(context)


    init {
        if (genres == null) {
            GlobalScope.launch(Dispatchers.IO) {
                genres = getGenres()
            }
        }
    }

    
    override suspend fun getCasts(movieId: Int): List<Actor> {
        if (ResponseWrapper.isNetworkConnected(context)){
            val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.getCredits(movieId))
            return when (result) {
                is Resource.Success ->
                    if (result.data.actorList == null) {
                        emptyList()
                    } else {
                        result.data.actorList.toActor()

                    }
                is Resource.Error -> {
                    Log.d("MyLog", result.message)
                    emptyList()
                }
            }

        }else return emptyList()


    }

    override suspend fun getGenres(): List<Genre> {
        if (ResponseWrapper.isNetworkConnected(context)){
            val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.getGenres())
            return when (result) {
                is Resource.Success ->
                    if (result.data.genres == null) {
                        emptyList()
                    } else {
                        result.data.genres.toGenre()
                    }
                is Resource.Error -> {
                    Log.d("MyLog", result.message)
                    emptyList()
                }

            }

        }else return emptyList()


    }

    override suspend fun nowPlaying(): List<Movie> {
        if (ResponseWrapper.isNetworkConnected(context)){
            val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.getNowPlaying())
            return when (result) {
                is Resource.Success ->
                    if (result.data.movieList == null) {
                        emptyList()
                    } else {
                        result.data.movieList.toMovie()
                    }
                is Resource.Error -> {
                    Log.d("MyLog", result.message)
                    emptyList()
                }
            }

        }else return emptyList()

    }

    override suspend fun saveMovieList(movies: List<Movie>) {
        val entities = mutableListOf<MovieEntity>()
        movies.forEach { movie ->
            entities.add(fromMovieToMovieEntity(movie))
        }
        db.movieDao().insertMovies(entities)
    }

    override suspend fun getSavedMovieList(): List<Movie> {
        val movies = mutableListOf<Movie>()
        val entities = db.movieDao().getNowPlaying()
        entities.forEach { entity ->
            movies.add(fromMovieEntityToMovie(entity))
        }
        return movies
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


    private fun List<JsonActor>.toActor(): List<Actor> {
        val newList = mutableListOf<Actor>()
        this.forEach {
            val newId: Int? = it.id
            val newName: String? = it.name
            if (newId != null && newName != null) {
                newList.add(
                    Actor(
                        id = newId,
                        name = newName,
                        imageUrl = getProfilePictureUrl() + it.profilePath
                    )
                )
            }
        }
        return newList
    }
    private fun List<JsonGenre>.toGenre(): List<Genre>{
        val newList = mutableListOf<Genre>()
        this.forEach {
            val newId: Int? = it.id
            val newName: String? = it.name
            if (newId != null && newName != null) {
                newList.add(
                    Genre(
                        id = it.id,
                        name = it.name
                    )
                )
            }
        }
        return newList
    }

    private fun List<JsonMovie>.toMovie(): List<Movie> {
        val movies = mutableListOf<Movie>()
        this.forEach {
            val newId: Int? = it.id
            val newTitle: String? = it.title
            val newOverview: String? = it.overview
            if (newId != null && newTitle != null && newOverview != null) {
                movies.add(
                    Movie(
                        id = newId,
                        title = newTitle,
                        genres = it.genreIds?.let { id -> loadGenres(id) },
                        imageUrl = getPosterUrl() + it.posterPath,
                        reviewCount = it.voteCount,
                        pgAge = it.adult?.let { adult -> checkAdultContent(adult) },
                        rating = ratingDoubleToInt(it.voteAverage),
                        storyLine = newOverview
                    )
                )
            }
        }
        return movies
    }

    private fun fromMovieToMovieEntity(movie : Movie) : MovieEntity{
        return MovieEntity(
            id = movie.id,
            title = movie.title,
            genres = movie.genres,
            imageUrl = movie.imageUrl,
            reviewCount = movie.reviewCount,
            pgAge = movie.pgAge,
            rating = movie.rating,
            storyLine = movie.storyLine
        )
    }

    private fun fromMovieEntityToMovie(entity : MovieEntity) : Movie{
        return Movie(
            id = entity.id,
            title = entity.title,
            genres = entity.genres,
            imageUrl = entity.imageUrl,
            reviewCount = entity.reviewCount,
            pgAge = entity.pgAge,
            rating = entity.rating,
            storyLine = entity.storyLine
        )
    }

    private fun ratingDoubleToInt(tmdbRating: Double?) = tmdbRating?.div(2)?.toInt()

}