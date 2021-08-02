package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.MovieListType
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
    private val repository: MoviesRepository,
    listType: MovieListType
) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    var page: Int = 1

    init {
        loadMovieList(listType)
    }


    fun loadMovieList(listType: MovieListType) = viewModelScope.launch {
//        val localData: List<Movie> = withContext(Dispatchers.IO) {
//            repository.getSavedMovieListFromDB()
//        }
//
//        if (localData.isNotEmpty()) {
//            _movieList.postValue(localData)
//        }
        val remoteData: List<Movie> = withContext(Dispatchers.IO) {
            repository.getTypedListMoviesWithPage(page, listType)

        }

        if (remoteData.isNotEmpty()) {
            withContext(Dispatchers.IO) {
                // repository.saveMovieListToDB(remoteData)
                //val updatedLocalData = repository.getSavedMovieListFromDB()
                _movieList.postValue(remoteData)
            }
        }

    }
}