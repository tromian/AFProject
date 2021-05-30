package com.tromian.game.afproject.data.network.tmdbapi

import com.tromian.game.afproject.data.network.responses.NowPlaying
import com.tromian.game.afproject.data.network.responses.ConfigurationResponse
import com.tromian.game.afproject.data.network.responses.CreditsResponse
import com.tromian.game.afproject.data.network.responses.GenresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

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