package com.tromian.game.afproject

class AppConstants {
    companion object {
        var TMDB_API_KEY = BuildConfig.TMDB_API_KEY
        val BASE_URL_TMDB = "https://api.themoviedb.org/3/"
        val LANGUAGE = "ru"

        val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/"
        val POSTER_SIZE = "/w500"
        val PROFILE_SIZE = "/w185"

        val ADULT_CONTENT_AGE = 16
        val NOT_ADULT_CONTENT_AGE = 13

        val LOG = "MyLog"
        val NETWORK_PAGE_SIZE = 20
    }


}