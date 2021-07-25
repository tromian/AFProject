package com.tromian.game.afproject.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.R
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.domain.repository.MoviesRepository
import com.tromian.game.afproject.presentation.view.MainActivity
import com.tromian.game.afproject.presentation.view.adapters.MovieListAdapter
import com.tromian.game.afproject.presentation.viewmodels.FavouriteViewModel
import com.tromian.game.afproject.presentation.viewmodels.MovieSearchVM
import com.tromian.game.afproject.presentation.viewmodels.MoviesViewModel

class FragmentFavourite : Fragment(R.layout.fragment_favourite_movies) {

    lateinit var viewModel : FavouriteViewModel
    lateinit var repository: MoviesRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).repository?.let {
            repository = it
        }

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FavouriteViewModel(repository) as T
            }
        }).get(FavouriteViewModel::class.java)

        val adapter = MovieListAdapter() { itemId ->
            openFragment(itemId)
        }

        viewModel.movieList.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })

        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvFavouriteMovieList)

        rvMovieList.adapter = adapter

    }
    private fun openFragment(itemId: Int) {
        val movie = viewModel.movieList.value?.get(itemId)
        if (movie!=null){
            val action = FragmentFavouriteDirections.actionFragmentFavouriteToFragmentDetails(movie)
            findNavController().navigate(action)
        }
    }

}