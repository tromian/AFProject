package com.tromian.game.afproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.model.models.Actor
import com.tromian.game.afproject.model.models.Movie
import com.tromian.game.afproject.model.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
        val app : Application,
        val repository : MoviesRepository
) : AndroidViewModel(app) {

    var actorList = MutableLiveData<List<Actor>>()

    fun getActors(movieId : Int) = viewModelScope.launch(Dispatchers.IO) {
        actorList.postValue(repository.getCasts(movieId))

    }


}