package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tromian.game.afproject.Di
import com.tromian.game.afproject.domain.MovieListType

class ViewModelFactory(
    private val listType: MovieListType = MovieListType.POPULAR,
    private val movieId: Int = 0
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            MoviesViewModel::class.java -> {
                MoviesViewModel(Di.moviesRepo,listType)
            }
            MovieSearchVM::class.java -> {
                MovieSearchVM(Di.moviesRepo)
            }
            MovieDetailsViewModel::class.java -> {
                MovieDetailsViewModel(movieId,Di.moviesRepo)
            }
            else -> throw IllegalStateException("Unknown view model class")
        }
        return viewModel as T
    }
}
