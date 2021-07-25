package com.tromian.game.afproject.data.network.tmdbapi

import com.tromian.game.afproject.data.network.responses.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query(value = "page") page: Int = 1
    ): Response<NowPlaying>

    @GET("configuration")
    suspend fun getConfiguration(): Response<ConfigurationResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") movieId: Int): Response<CreditsResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresResponse>
    @GET("search/movie")
    suspend fun search(
        @Query(value = "query") query: String
    ) : Response<SearchResponse>

}