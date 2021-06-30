package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieSearchVM(
    val repository: MoviesRepository
) : ViewModel() {

    var movieList = MutableLiveData<List<Movie>>()

    fun searchMovie(title: String) = viewModelScope.launch(Dispatchers.IO) {
        delay(2_000)
        val searchResult = repository.searchMovie(title)
        if (searchResult.isNotEmpty()){
            movieList.postValue(searchResult)
        }
    }


}