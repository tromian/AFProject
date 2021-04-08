package com.tromian.game.afproject.model.tmdbapi

import com.tromian.game.afproject.model.data.NowPlaying
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {

    @GET("movie/now_playing")
   fun getNowPlaying() : Call<NowPlaying>

}