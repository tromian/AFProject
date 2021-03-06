package com.tromian.game.afproject.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.R
import com.tromian.game.afproject.data.repository.MoviesDataRepository
import com.tromian.game.afproject.presentation.view.MainActivity
import com.tromian.game.afproject.presentation.view.adapters.MovieListAdapter
import com.tromian.game.afproject.presentation.viewmodels.MoviesViewModel

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private lateinit var viewModel : MoviesViewModel
    private lateinit var repository: MoviesDataRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).repository?.let {
            repository = it
        }

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MoviesViewModel(repository) as T
            }
        }).get(MoviesViewModel::class.java)

        val adapter = MovieListAdapter() { itemId ->
            openFragment(itemId)
        }
        viewModel.movieList.observe(requireActivity(), Observer {
                adapter.submitList(it)
        })

        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        rvMovieList.adapter = adapter

    }

    private fun openFragment(itemId: Int) {
        val bundle = Bundle()
        val movie = viewModel.movieList.value?.get(itemId)
        bundle.putSerializable("movie",movie)
        findNavController().navigate(R.id.fragmentMoviesDetails,bundle)

    }

}