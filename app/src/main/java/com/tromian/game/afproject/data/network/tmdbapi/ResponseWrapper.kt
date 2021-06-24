package com.tromian.game.afproject.data.network.tmdbapi

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_MOBILE
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build
import com.tromian.game.afproject.domain.Resource
import retrofit2.Response


object ResponseWrapper {

    fun <T> safeApiResponse(apiMethod: Response<T>): Resource<T> {

        return if (apiMethod.isSuccessful) {
            val newBody: T? = apiMethod.body()
            if (newBody != null){
                Resource.Success(newBody)
            }else {
                Resource.Error(apiMethod.message())
            }
        } else Resource.Error(apiMethod.message())
    }

    fun isNetworkConnected(context: Context) : Boolean{
        val conManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = conManager.activeNetwork ?: return false
            val capabilities = conManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                else -> false
            }

        }else {
            conManager.activeNetworkInfo?.run {
                return when(type){
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    else -> false
                }
            }
        }
        return false
        }


}