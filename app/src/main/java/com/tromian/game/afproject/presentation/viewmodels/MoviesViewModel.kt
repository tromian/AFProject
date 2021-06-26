package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
       val repository : MoviesRepository
) : ViewModel() {

    var movieList = MutableLiveData<List<Movie>>()
    var page: Int = 1

    init {
        loadNowPlaying()
    }


    fun loadNowPlaying() = viewModelScope.launch{
        val localData: List<Movie> = withContext(Dispatchers.IO){
            repository.getSavedMovieList()
        }

        if (localData.isNotEmpty()){
            movieList.postValue(localData)
        }
        val remoteData: List<Movie> = withContext(Dispatchers.IO){
                repository.nowPlaying(page)
        }

        if (remoteData.isNotEmpty()){
            withContext(Dispatchers.IO){
                repository.saveMovieList(remoteData)
                val updatedLocalData = repository.getSavedMovieList()
                movieList.postValue(updatedLocalData)
            }
        }

    }

//    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
//        movieList.postValue(repository.nowPlaying())
//    }

}