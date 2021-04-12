package com.tromian.game.afproject.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.AppConstants
import com.tromian.game.afproject.model.data.NowPlaying
import com.tromian.game.afproject.model.models.Movie
import com.tromian.game.afproject.model.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(
       val app : Application,
       val repository : MoviesRepository
) : AndroidViewModel(app) {

    var movieList = MutableLiveData<List<Movie>>()

    init {
        Log.d(AppConstants.LOG,"init MoviesViewModel")
        getMovies()
    }

    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(AppConstants.LOG,"start Coroutine getMovies")
        movieList.postValue(repository.nowPlaying())
        Log.d(AppConstants.LOG,"end Coroutine getMovies")
    }

}