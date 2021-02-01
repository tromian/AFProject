package com.tromian.game.afproject.ui.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tromian.game.afproject.R
import com.tromian.game.afproject.SomeItemClickListener
import com.tromian.game.afproject.model.adapters.ActorsListAdapter
import com.tromian.game.afproject.model.models.Movie

class FragmentMoviesDetails(val movie: Movie) : Fragment(R.layout.fragment_movie_details) {
    private var someFragmentClickListener: SomeItemClickListener? = null
    val TAG = "Tag"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bind(view)


        val listActors = movie.actors
        Log.d(TAG, listActors.toString())
        val rvActorsList = view.findViewById<RecyclerView>(R.id.rvActorsList)
        val adapter = ActorsListAdapter(requireContext())

        adapter.submitList(listActors)

        rvActorsList.adapter = adapter
        rvActorsList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        view.findViewById<TextView>(R.id.tvBack).apply {
            setOnClickListener {
                someFragmentClickListener?.onBackButtonClicked()
            }
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

    fun bind(view: View) {

        val poster: ImageView = view.findViewById(R.id.ivBackgroundPoster)
        val age: TextView = view.findViewById(R.id.tvAge)
        val title: TextView = view.findViewById(R.id.tvTitle)
        val tags: TextView = view.findViewById(R.id.tvTag)
        val storyline: TextView = view.findViewById(R.id.storylineText)
        val reviews: TextView = view.findViewById(R.id.tvReviews)
        try {
            Glide.with(view.context)
                    .load(movie.imageUrl)
                    .into(poster)

        } catch (e: Exception) {
            Log.d("glide", e.toString())
        }
        age.text = movie.pgAge.toString()
        title.text = movie.title
        storyline.text = movie.storyLine
        age.text = movie.pgAge.toString() + "+"
        tags.text = movie.genres.toString()
        reviews.text = "${movie.reviewCount} Reviews"

    }


}