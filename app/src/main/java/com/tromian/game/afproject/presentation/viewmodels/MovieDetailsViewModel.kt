package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.LiveData
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

    private val _actorList = MutableLiveData<List<Actor>>()
    val actorList : LiveData<List<Actor>> = _actorList

    init {
        getActors(movieId)
    }

    fun getActors(movieId : Int) = viewModelScope.launch(Dispatchers.IO) {
        _actorList.postValue(repository.getMovieCastsByIdInApi(movieId))
    }

    fun saveMovie(movie: Movie) = viewModelScope.launch {
        repository.saveMovieToDB(movie)
    }
    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        repository.deleteMovieFromDB(movie)
    }

}