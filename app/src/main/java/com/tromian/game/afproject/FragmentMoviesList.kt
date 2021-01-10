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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentMoviesList() : Fragment(R.layout.fragment_movies_list) {

    private var someFragmentClickListener: SomeItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var listMovies : List<Movie>? = null
        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        lifecycleScope.launch {
            listMovies = loadMovies(requireContext())
            val adapter = listMovies?.let { MoviesListAdapter(requireContext(), it) }
            rvMovieList.adapter = adapter

            rvMovieList.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SomeItemClickListener) {
            someFragmentClickListener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        someFragmentClickListener = null
    }


}