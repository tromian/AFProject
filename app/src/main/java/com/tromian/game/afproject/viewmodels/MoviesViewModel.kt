package com.tromian.game.afproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.model.models.Movie
import com.tromian.game.afproject.model.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
        val app : Application,
        private val repository : MoviesRepository
) : AndroidViewModel(app) {

    var movieList = MutableLiveData<List<Movie>>()

    init {
        getMovies()
    }

    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        val list = repository.getMoviesList(app)
        movieList.postValue(list)
    }

}