package com.tromian.game.afproject


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.model.Movie

class FragmentMoviesDetails(val movie: Movie) : Fragment(R.layout.fragment_movie_details) {
    private var someFragmentClickListener : SomeItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvActorsList = view.findViewById<RecyclerView>(R.id.rvActorsList)
        val list = movie.actors
        val adapter = ActorsListAdapter(requireContext(), list)
        rvActorsList.adapter = adapter
        rvActorsList.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)


        view.findViewById<TextView>(R.id.tvBack).apply {
            setOnClickListener {
                someFragmentClickListener?.onBackButtonClicked()
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SomeItemClickListener){
            someFragmentClickListener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        someFragmentClickListener = null
    }



}