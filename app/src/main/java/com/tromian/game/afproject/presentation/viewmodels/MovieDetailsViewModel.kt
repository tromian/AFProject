package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Actor
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
        private val movieId: Int,
        private val repository : MoviesRepository
) : ViewModel() {

    var actorList = MutableLiveData<List<Actor>>()

    init {
        getActors(movieId)
    }

    fun getActors(movieId : Int) = viewModelScope.launch(Dispatchers.IO) {
        actorList.postValue(repository.getCasts(movieId))
    }

    fun saveMovie(movie: Movie) = viewModelScope.launch {
        repository.saveMovie(movie)
    }
    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }

}