package com.tromian.game.afproject.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.R
import com.tromian.game.afproject.model.adapters.MovieListAdapter
import com.tromian.game.afproject.model.data.loadMovies
import com.tromian.game.afproject.model.models.Movie
import com.tromian.game.afproject.ui.MainActivity
import com.tromian.game.afproject.viewmodels.MoviesViewModel
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    lateinit var viewModel: MoviesViewModel
    private var listMovies : List<Movie>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).moviesViewModel


        val adapter = MovieListAdapter() { itemId ->
            openFragment(itemId)
        }

        viewModel.movieList.observe(requireActivity(), Observer {
            adapter.submitList(it)
            listMovies = it
        })

        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        rvMovieList.adapter = adapter
        rvMovieList.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

    }

    private fun openFragment(itemId: Int) {

        val movie = listMovies?.get(itemId)
        val activity = requireActivity() as MainActivity
        if (movie != null) {
            activity.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.main_container, FragmentMoviesDetails(movie))
                    .commit()
        }

    }


}