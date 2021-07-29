package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieSearchVM(
    val repository: MoviesRepository
) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList
    var page = 1

    fun searchMovie(title: String) = viewModelScope.launch(Dispatchers.IO) {
        val searchResult = repository.searchMoviesByTitleInApi(page, title)
        if (searchResult.isNotEmpty()) {
            _movieList.postValue(searchResult)
        }
    }


}