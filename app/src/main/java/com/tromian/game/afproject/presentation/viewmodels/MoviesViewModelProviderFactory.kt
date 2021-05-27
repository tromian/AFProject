package com.tromian.game.afproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tromian.game.afproject.domain.repository.MoviesRepository

class MoviesViewModelProviderFactory(
    val app : Application,
    private val repository : MoviesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(app, repository) as T
    }

}