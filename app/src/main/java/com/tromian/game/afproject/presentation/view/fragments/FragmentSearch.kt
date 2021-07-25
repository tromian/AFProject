package com.tromian.game.afproject.presentation.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.Di
import com.tromian.game.afproject.R
import com.tromian.game.afproject.presentation.view.adapters.MovieListAdapter
import com.tromian.game.afproject.presentation.viewmodels.MovieSearchVM

class FragmentSearch : Fragment(R.layout.fragment_search) {

    lateinit var viewModel : MovieSearchVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieSearchVM(Di.moviesRepo) as T
            }
        }).get(MovieSearchVM::class.java)

        val editText : EditText = view.findViewById(R.id.editText)
        editText.addTextChangedListener {

            viewModel.searchMovie(it.toString())
        }

        val adapter = MovieListAdapter(){ itemId ->
            openFragment(itemId)
        }

        viewModel.movieList.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })

        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        rvMovieList.adapter = adapter

    }

    private fun openFragment(itemId: Int) {
        val movie = viewModel.movieList.value?.get(itemId)
        if (movie!=null){
            val action = FragmentSearchDirections.actionFragmentSearchToFragmentMoviesDetails(movie)
            findNavController().navigate(action)
        }
    }
}