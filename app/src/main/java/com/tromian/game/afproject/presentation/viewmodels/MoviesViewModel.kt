package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tromian.game.afproject.domain.MovieListType
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MoviesViewModel(
    repository: MoviesRepository,
    listType: MovieListType
) : ViewModel() {

    var listTypeMovies = listType
    //private val _movieList = MutableLiveData<List<Movie>>()
    // val movieList: LiveData<List<Movie>> = _movieList


    var _movieListFlow = repository.getMovieListResultStream(listTypeMovies)
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    var page: Int = 1

    init {
        //loadMovieList(listType)
    }


//    fun loadMovieList(listType: MovieListType) = viewModelScope.launch {
////        val localData: List<Movie> = withContext(Dispatchers.IO) {
////            repository.getSavedMovieListFromDB()
////        }
////
////        if (localData.isNotEmpty()) {
////            _movieList.postValue(localData)
////        }
//        val remoteData: List<Movie> = withContext(Dispatchers.IO) {
//            repository.getTypedListMoviesWithPage(page, listType)
//        }
//
//        if (remoteData.isNotEmpty()) {
//            withContext(Dispatchers.IO) {
//                 //repository.saveMovieListToDB(remoteData)
//                val updatedLocalData = repository.getSavedMovieListFromDB()
//                _movieList.postValue(remoteData)
//            }
//        }
//
//    }
}