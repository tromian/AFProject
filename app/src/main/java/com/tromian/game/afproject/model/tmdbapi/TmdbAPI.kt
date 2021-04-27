package com.tromian.game.afproject.model.tmdbapi

import com.tromian.game.afproject.model.data.NowPlaying
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): Response<NowPlaying>

    @GET("configuration")
    suspend fun getConfiguration(): Response<ConfigurationResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") movieId: Int): Response<CreditsResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresResponse>

}