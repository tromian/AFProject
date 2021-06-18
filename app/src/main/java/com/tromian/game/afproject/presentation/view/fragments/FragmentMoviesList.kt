package com.tromian.game.afproject.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.R
import com.tromian.game.afproject.data.repository.MoviesDataRepository
import com.tromian.game.afproject.presentation.view.MainActivity
import com.tromian.game.afproject.presentation.view.adapters.MovieListAdapter
import com.tromian.game.afproject.presentation.viewmodels.MoviesViewModel

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private var viewModel: MoviesViewModel? = null
    private lateinit var repository: MoviesDataRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).repository?.let {
            repository = it
        }
        if (viewModel == null){
            viewModel = MoviesViewModel(repository)
        }
        val adapter = MovieListAdapter() { itemId ->
            openFragment(itemId)
        }
        viewModel?.let {
            it.movieList.observe(requireActivity(), Observer {
                adapter.submitList(it)
            })
        }


        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        rvMovieList.adapter = adapter

    }

    private fun openFragment(itemId: Int) {
        val bundle = Bundle()
        val movie = viewModel?.let {
            it.movieList.value?.get(itemId)
        }
        bundle.putSerializable("movie",movie)
        findNavController().navigate(R.id.fragmentMoviesDetails,bundle)

    }

}