package com.tromian.game.afproject.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tromian.game.afproject.model.repository.MoviesRepository

class MovieDetailsVMFactory(
            val app : Application,
            private val repository : MoviesRepository
    ) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return MovieDetailsViewModel(app, repository) as T
    }

}
