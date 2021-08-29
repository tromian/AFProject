package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    init {
        loadFavourites()
    }

    fun loadFavourites() {
        viewModelScope.launch {
            val localFavouriteList = repository.getFavouriteMovieListFromDB()
            _movieList.postValue(localFavouriteList)
        }
    }

}