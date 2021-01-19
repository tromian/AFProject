package com.tromian.game.afproject

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.data.loadMovies
import com.tromian.game.afproject.model.Movie
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    var listMovies : List<Movie>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieListAdapter() { itemId ->
            openFragment(itemId)
        }
        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        rvMovieList.adapter = adapter
        rvMovieList.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        lifecycleScope.launch {
            listMovies = loadMovies(requireContext())
            adapter.submitList(listMovies)

        }


    }

    private fun openFragment(itemId : Int){

        val movie = listMovies?.get(itemId)
        val activity = requireActivity() as MainActivity
        if (movie != null){
            activity.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.main_container, FragmentMoviesDetails(movie))
                    .commit()
        }

    }


}