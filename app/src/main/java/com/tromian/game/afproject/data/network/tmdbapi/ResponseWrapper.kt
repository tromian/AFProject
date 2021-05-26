package com.tromian.game.afproject.data.network.tmdbapi

import com.tromian.game.afproject.domain.Resource
import retrofit2.Response


object ResponseWrapper {

    fun <T> safeApiResponse(apiMethod: Response<T>): Resource<T> {
        return if (apiMethod.isSuccessful) {
            Resource.Success(apiMethod.body()!!)
        } else Resource.Error(apiMethod.message())
    }

}