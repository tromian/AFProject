package com.tromian.game.afproject.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.R
import com.tromian.game.afproject.appComponent
import com.tromian.game.afproject.presentation.view.adapters.MovieListAdapter
import com.tromian.game.afproject.presentation.viewmodels.FavouriteViewModel
import com.tromian.game.afproject.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class FragmentFavourite : Fragment(R.layout.fragment_favourite_movies) {

    @Inject
    lateinit var factory: ViewModelFactory.Factory

    private val viewModel by viewModels<FavouriteViewModel> {
        factory.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieListAdapter() { itemId ->
            openFragment(itemId)
        }

        viewModel.loadFavourites()

        viewModel.movieList.observe(requireActivity(), {
            adapter.submitList(it)
        })

        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvFavouriteMovieList)

        rvMovieList.adapter = adapter

    }

    private fun openFragment(itemId: Int) {
        val movie = viewModel.movieList.value?.get(itemId)
        if (movie != null) {
            val action = FragmentFavouriteDirections.actionFragmentFavouriteToFragmentDetails(movie)
            findNavController().navigate(action)
        }
    }

}