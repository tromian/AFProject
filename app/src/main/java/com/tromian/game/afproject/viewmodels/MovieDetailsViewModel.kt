package com.tromian.game.afproject.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tromian.game.afproject.model.models.Movie

class MovieDetailsViewModel : ViewModel() {
    var movie = MutableLiveData<Movie>()



}