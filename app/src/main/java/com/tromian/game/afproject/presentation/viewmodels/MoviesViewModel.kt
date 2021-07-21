package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
       private val repository : MoviesRepository
) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<Movie>> = _movieList

    var page: Int = 1

    init {
        loadNowPlaying()
    }


    fun loadNowPlaying() = viewModelScope.launch{
        val localData: List<Movie> = withContext(Dispatchers.IO){
            repository.getSavedMovieList()
        }

        if (localData.isNotEmpty()){
            _movieList.postValue(localData)
        }
        val remoteData: List<Movie> = withContext(Dispatchers.IO){
                repository.nowPlaying(page)
        }

        if (remoteData.isNotEmpty()){
            withContext(Dispatchers.IO){
                repository.saveMovieList(remoteData)
                val updatedLocalData = repository.getSavedMovieList()
                _movieList.postValue(updatedLocalData)
            }
        }

    }

//    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
//        movieList.postValue(repository.nowPlaying())
//    }

}