package com.tromian.game.afproject.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
       val repository : MoviesRepository
) : ViewModel() {

    var movieList = MutableLiveData<List<Movie>>()

    init {
        loadNowPlaying()
    }

    fun loadNowPlaying() = viewModelScope.launch{
        val localData: List<Movie> = repository.getSavedMovieList()

        if (localData.isNotEmpty()){
            movieList.postValue(localData)
        }
        val remoteData: List<Movie> = withContext(Dispatchers.IO){
            repository.nowPlaying()
        }

        if (remoteData.isNotEmpty()){
            delay(5_000)
            withContext(Dispatchers.IO){
                repository.saveMovieList(remoteData)
                movieList.postValue(remoteData)
            }
        }



    }

//    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
//        movieList.postValue(repository.nowPlaying())
//    }

}