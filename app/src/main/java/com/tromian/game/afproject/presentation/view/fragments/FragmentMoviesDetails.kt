package com.tromian.game.afproject.presentation.view.fragments


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
import com.tromian.game.afproject.data.repository.MoviesDataRepository
import com.tromian.game.afproject.presentation.view.adapters.ActorsListAdapter
import com.tromian.game.afproject.domain.models.Movie
import com.tromian.game.afproject.presentation.view.MainActivity
import com.tromian.game.afproject.presentation.viewmodels.MovieDetailsViewModel
import com.tromian.game.afproject.presentation.viewmodels.MoviesViewModel

class FragmentMoviesDetails : Fragment(R.layout.fragment_movie_details) {
    private var someFragmentClickListener: SomeItemClickListener? = null
    private lateinit var repository: MoviesDataRepository
    private lateinit var movie: Movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        repository = (activity as MainActivity).repository
        movie = arguments?.getSerializable("movie") as Movie
        val movieId = movie.id
        val viewModel = MovieDetailsViewModel(movieId, repository)


        bind(view)

        val rvActorsList = view.findViewById<RecyclerView>(R.id.rvActorsList)
        val adapter = ActorsListAdapter(requireContext())

        viewModel.actorList.observe(requireActivity(), {
            adapter.submitList(it)
        })


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
        tags.text = movie.genres
        reviews.text = "${movie.reviewCount} Reviews"
        movie.rating
        setStars(movie.rating,view)

    }

    private fun setStars(rating: Int?, view: View){

        val star1: ImageView = view.findViewById(R.id.ivStar1)
        val star2: ImageView = view.findViewById(R.id.ivStar2)
        val star3: ImageView = view.findViewById(R.id.ivStar3)
        val star4: ImageView = view.findViewById(R.id.ivStar4)
        val star5: ImageView = view.findViewById(R.id.ivStar5)

        when (rating) {
            1 -> star1.setImageResource(R.drawable.ic_star_icon_fill)

            2 -> {
                star1.setImageResource(R.drawable.ic_star_icon_fill)
                star2.setImageResource(R.drawable.ic_star_icon_fill)
            }
            3 -> {
                star1.setImageResource(R.drawable.ic_star_icon_fill)
                star2.setImageResource(R.drawable.ic_star_icon_fill)
                star3.setImageResource(R.drawable.ic_star_icon_fill)
            }
            4 -> {
                star1.setImageResource(R.drawable.ic_star_icon_fill)
                star2.setImageResource(R.drawable.ic_star_icon_fill)
                star3.setImageResource(R.drawable.ic_star_icon_fill)
                star4.setImageResource(R.drawable.ic_star_icon_fill)
            }
            5 -> {
                star1.setImageResource(R.drawable.ic_star_icon_fill)
                star2.setImageResource(R.drawable.ic_star_icon_fill)
                star3.setImageResource(R.drawable.ic_star_icon_fill)
                star4.setImageResource(R.drawable.ic_star_icon_fill)
                star5.setImageResource(R.drawable.ic_star_icon_fill)
            }
            else -> {
            }

        }

    }
}