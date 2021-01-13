package com.tromian.game.afproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.data.loadMovies
import com.tromian.game.afproject.model.Movie
import kotlinx.coroutines.launch

class FragmentMoviesList() : Fragment(R.layout.fragment_movies_list) {

    val TAG = "MyTag"

    private var someFragmentClickListener: SomeItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listMovies : List<Movie>? = null
        Log.d(TAG, "empty List created")
        Log.d(TAG, "${listMovies.toString()}")
        val adapter2 = MovieListAdapter(requireContext())
        Log.d(TAG, "adapter2 created")
        adapter2.submitList(listMovies)
        Log.d(TAG, "List Submit Empty")
        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        rvMovieList.adapter = adapter2
        rvMovieList.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        lifecycleScope.launch {
            Log.d(TAG, "Start coroutine")
            listMovies = loadMovies(requireContext())
            Log.d(TAG, "eMovies loaded to list")
            Log.d(TAG, "${listMovies.toString()}")
            adapter2.submitList(listMovies)
            Log.d(TAG, "submit loaded list to adapter")

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