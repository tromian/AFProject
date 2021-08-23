package com.tromian.game.afproject.data.network.tmdbapi

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tromian.game.afproject.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDBService {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", AppConstants.TMDB_API_KEY)
            .addQueryParameter("language", AppConstants.LANGUAGE)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)

    }

    val logger = HttpLoggingInterceptor(
        HttpLoggingInterceptor.Logger { Log.d("API", it) }
    ).apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    }


    private val tmdbClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logger)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(tmdbClient)
        .baseUrl(AppConstants.BASE_URL_TMDB)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val tmdbApi = retrofit.create(TmdbAPI::class.java)

}