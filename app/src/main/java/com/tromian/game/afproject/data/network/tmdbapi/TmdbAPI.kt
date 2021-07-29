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
    ): Response<MovieListResponse>

    @GET("movie/{list_type}")
    suspend fun getMovieListByListType(
        @Path(value = "list_type") list_type: ListType = ListType.now_playing,
        @Query(value = "page") page: Int = 1
    ): Response<MovieListResponse>

    @GET("configuration")
    suspend fun getConfiguration(): Response<ConfigurationResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") movieId: Int): Response<CreditsResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresResponse>
    @GET("search/movie")
    suspend fun search(
        @Query(value = "page") page: Int = 1,
        @Query(value = "query") query: String
    ) : Response<MovieListResponse>

    enum class ListType{
        now_playing,
        popular,
        top_rated,
        upcoming
    }

}