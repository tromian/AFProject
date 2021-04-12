package com.tromian.game.afproject.model.repository

import android.util.Log
import com.tromian.game.afproject.AppConstants
import com.tromian.game.afproject.model.Resource
import com.tromian.game.afproject.model.tmdbapi.ConfigurationResponse

import retrofit2.Response


open class BaseRepository {


    fun <T> safeApiResponse(apiMethod: Response<T>) : Resource<T> {
        Log.d(AppConstants.LOG,"start safeApiResponse")
        return if (apiMethod.isSuccessful){
            Log.d(AppConstants.LOG,"safeApiResponse Successful")
            Resource.Success(apiMethod.body()!!)
        }else Resource.Error(apiMethod.message())
    }



}