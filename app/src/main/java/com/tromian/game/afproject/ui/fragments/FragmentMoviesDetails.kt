package com.tromian.game.afproject.ui.fragments


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tromian.game.afproject.R
import com.tromian.game.afproject.SomeItemClickListener
import com.tromian.game.afproject.ui.adapters.ActorsListAdapter
import com.tromian.game.afproject.model.models.Movie
import com.tromian.game.afproject.ui.MainActivity
import com.tromian.game.afproject.viewmodels.MoviesViewModel

class FragmentMoviesDetails(val itemId: Int) : Fragment(R.layout.fragment_movie_details) {
    private var someFragmentClickListener: SomeItemClickListener? = null
    lateinit var viewModel: MoviesViewModel
    private lateinit var movie: Movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (activity as MainActivity).moviesViewModel
        movie = viewModel.movieList.value?.get(itemId) as Movie

        bind(view)

        val listActors = movie.actors

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

        Glide.with(view.context)
                .load(movie.imageUrl)
                .error(R.drawable.film_placeholder)
                .into(poster)

        age.text = movie.pgAge.toString()
        title.text = movie.title
        storyline.text = movie.storyLine
        age.text = movie.pgAge.toString() + "+"
        tags.text = movie.genreIds.toString()
        reviews.text = "${movie.reviewCount} Reviews"

    }


}