package com.tromian.game.afproject.data.repository


import android.content.Context
import android.media.midi.MidiOutputPort
import android.util.Log
import com.tromian.game.afproject.data.db.*
import com.tromian.game.afproject.data.network.models.JsonMovie
import com.tromian.game.afproject.data.network.tmdbapi.ApiFactory
import com.tromian.game.afproject.data.network.tmdbapi.ResponseWrapper
import com.tromian.game.afproject.data.network.tmdbapi.TmdbAPI
import com.tromian.game.afproject.domain.MovieListType
import com.tromian.game.afproject.domain.Resource
import com.tromian.game.afproject.domain.models.Actor
import com.tromian.game.afproject.domain.models.Genre
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesDataRepository(val context: Context) : MoviesRepository {

    private var genres: List<Genre>? = null
    private val db = MoviesDB.getInstance(context)

    init {
        if (genres == null) {
            CoroutineScope(Dispatchers.IO).launch{
                getMovieGenreList()
            }
        }
    }

    override suspend fun getFavouriteMovieListFromDB() : List<Movie>{
        return db.movieDao().getFavourite().map {
            it.toMovie()
        }
    }

    suspend fun getMovieGenreList(){
        val localGenres = db.genreDao().getGenreList()
        if (localGenres.isNotEmpty()){
            genres = localGenres.map {
                it.toGenre()
            }
        }else{
            val remoteGenres = getAllGenreListFromApi()
            genres = remoteGenres
            db.genreDao().insertListGenre(remoteGenres.map {
                it.toGenreEntity()
            })
        }
    }

    override suspend fun getMovieCastsByIdInApi(movieId: Int): List<Actor> {
        return if (ResponseWrapper.isNetworkConnected(context)){
            val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.getCredits(movieId))
            when (result) {
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

        }else emptyList()

    }

    override suspend fun getAllGenreListFromApi(): List<Genre> {
        return if (ResponseWrapper.isNetworkConnected(context)){
            val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.getGenres())
            when (result) {
                is Resource.Success ->
                    if (result.data.genres == null) {
                        emptyList()
                    } else {
                        result.data.genres.toGenre()
                    }
                is Resource.Error -> {
                    Log.d("MyLog", result.message)
                    return emptyList()
                }
            }

        }else emptyList()

    }

    override suspend fun getTypedListMoviesWithPage(
        page: Int,
        type: MovieListType
    ): List<Movie> {
        return if (ResponseWrapper.isNetworkConnected(context)){
            val result = ResponseWrapper.safeApiResponse(
                ApiFactory.tmdbApi.getMovieListByListType(type.toTmdbType())
            )
            when (result) {
                is Resource.Success ->
                        result.data.movieList.toMovie()
                is Resource.Error -> {
                    Log.d("MyLog", result.message)
                    emptyList()
                }
            }

        }else emptyList()

    }

    override suspend fun saveMovieListToDB(movies: List<Movie>) {
        val entities = movies.map {
            it.toMovieEntity()
        }
        db.movieDao().insertMovies(entities)
    }

    override suspend fun getSavedMovieListFromDB(): List<Movie> {
        val movies = db.movieDao().getNowPlaying().map {
            it.toMovie()
        }
        return movies
    }

    override suspend fun saveMovieToDB(movie: Movie) {
        movie.toMovieEntity().also {
            db.movieDao().insertMovie(it)
        }
    }

    override suspend fun deleteMovieFromDB(movie: Movie) {
        movie.toMovieEntity().also {
            db.movieDao().deleteMovie(it)
        }
    }

    override suspend fun searchMoviesByTitleInApi(page: Int,title: String): List<Movie> {
        return if (ResponseWrapper.isNetworkConnected(context)){
            val result = ResponseWrapper.safeApiResponse(ApiFactory.tmdbApi.search(page,title))
            when(result){
                is Resource.Success ->
                        result.data.movieList.toMovie()
                is Resource.Error -> {
                    Log.d("MyLog", result.message)
                    emptyList()
                }
            }
        } else emptyList()
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
        } else ""
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
    private fun ratingDoubleToInt(tmdbRating: Double?) = tmdbRating?.div(2)?.toInt()
}