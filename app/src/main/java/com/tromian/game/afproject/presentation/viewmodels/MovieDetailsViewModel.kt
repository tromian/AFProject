package com.tromian.game.afproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.domain.models.Actor
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
        val repository : MoviesRepository
) : ViewModel() {

    var actorList = MutableLiveData<List<Actor>>()

    fun getActors(movieId : Int) = viewModelScope.launch(Dispatchers.IO) {
        actorList.postValue(repository.getCasts(movieId))

    }


}