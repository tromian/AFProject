package com.tromian.game.afproject.data.db

import com.tromian.game.afproject.AppConstants
import com.tromian.game.afproject.data.db.entityes.GenreEntity
import com.tromian.game.afproject.data.db.entityes.MovieEntity
import com.tromian.game.afproject.data.network.models.JsonActor
import com.tromian.game.afproject.data.network.models.JsonGenre
import com.tromian.game.afproject.data.network.tmdbapi.TmdbAPI
import com.tromian.game.afproject.domain.MovieListType
import com.tromian.game.afproject.domain.models.Actor
import com.tromian.game.afproject.domain.models.Genre
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.presentation.view.fragments.FragmentMoviesListDirections

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        genres = this.genres,
        imageUrl = this.imageUrl,
        reviewCount = this.reviewCount,
        pgAge = this.pgAge,
        rating = this.rating,
        storyLine = this.storyLine,
        isLiked = this.isLiked
    )
}

fun MovieListType.toTmdbType() : TmdbAPI.ListType {
    return when (this) {
        MovieListType.NOW_PLAYING -> {
            TmdbAPI.ListType.now_playing
        }
        MovieListType.POPULAR -> {
            TmdbAPI.ListType.popular
        }
        MovieListType.TOP_RATED -> {
            TmdbAPI.ListType.top_rated
        }
        else -> {
            TmdbAPI.ListType.upcoming
        }
    }
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        genres = this.genres,
        imageUrl = this.imageUrl,
        reviewCount = this.reviewCount,
        pgAge = this.pgAge,
        rating = this.rating,
        storyLine = this.storyLine,
        isLiked = this.isLiked

    )
}

fun getPosterUrl(): String {
    return AppConstants.IMAGES_BASE_URL + AppConstants.POSTER_SIZE
}

fun getProfilePictureUrl(): String {
    return AppConstants.IMAGES_BASE_URL + AppConstants.PROFILE_SIZE
}

fun checkAdultContent(adult: Boolean): Int {
    return if (adult) AppConstants.ADULT_CONTENT_AGE
    else AppConstants.NOT_ADULT_CONTENT_AGE
}

fun List<JsonActor>.toActor(): List<Actor> {
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

fun List<JsonGenre>.toGenre(): List<Genre>{
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

fun GenreEntity.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun Genre.toGenreEntity(): GenreEntity {
    return GenreEntity(
        id = this.id,
        name = this.name
    )
}
