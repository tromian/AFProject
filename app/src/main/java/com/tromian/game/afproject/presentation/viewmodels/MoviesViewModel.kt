package com.tromian.game.afproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
       val repository : MoviesRepository
) : ViewModel() {

    var movieList = MutableLiveData<List<Movie>>()

    init {
        getMovies()
    }

    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        movieList.postValue(repository.nowPlaying())
    }

}