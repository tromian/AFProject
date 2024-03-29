package com.tromian.game.afproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tromian.game.afproject.domain.MovieListType
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MoviesViewModel(
    repository: MoviesRepository
) : ViewModel() {

    private var nowPlayingList = repository.getMovieListResultStream(MovieListType.NOW_PLAYING)
        .toStateFlow()

    private var popularList = repository.getMovieListResultStream(MovieListType.POPULAR)
        .toStateFlow()

    private var topRatedList = repository.getMovieListResultStream(MovieListType.TOP_RATED)
        .toStateFlow()

    private var upcomingList = repository.getMovieListResultStream(MovieListType.UPCOMING)
        .toStateFlow()
    
    fun loadList(listType: MovieListType): StateFlow<PagingData<Movie>> {
        return when (listType) {
            MovieListType.UPCOMING -> upcomingList
            MovieListType.POPULAR -> popularList
            MovieListType.NOW_PLAYING -> nowPlayingList
            MovieListType.TOP_RATED -> topRatedList
        }
    }

    private fun Flow<PagingData<Movie>>.toStateFlow() = this.cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

}
