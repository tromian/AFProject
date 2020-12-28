package com.tromian.game.afproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private var someFragmentClickListener: SomeItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)
        val list = getMoviesList()
        val adapter = MoviesListAdapter(requireContext(), list)
        rvMovieList.adapter = adapter
        rvMovieList.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

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


    private fun getMoviesList(): List<Movie> {

        val list = arrayListOf<Movie>()

        list.add(Movie("Avangers",
                "Action, Adventure, Drama",
                "+13",
                4,
                125,
                "",
                137,
                R.drawable.poster
        ))
        list.add(Movie("Avangers",
                "Action, Adventure, Drama",
                "+13",
                1,
                125,
                "",
                137,
                R.drawable.chris_evans
        ))
        list.add(Movie("Avangers",
                "Action, Adventure, Drama",
                "+13",
                2,
                125,
                "",
                137,
                R.drawable.poster
        ))
        list.add(Movie("Avangers",
                "Action, Adventure, Drama",
                "+13",
                3,
                125,
                "",
                137,
                R.drawable.poster
        ))
        list.add(Movie("Avangers",
                "Action, Adventure, Drama",
                "+13",
                4,
                125,
                "",
                137,
                R.drawable.poster
        ))
        list.add(Movie("Avangers",
                "Action, Adventure, Drama",
                "+13",
                4,
                125,
                "",
                137,
                R.drawable.poster
        ))
        list.add(Movie("Avangers",
                "Action, Adventure, Drama",
                "+13",
                4,
                125,
                "",
                137,
                R.drawable.poster
        ))

        return list
    }


}