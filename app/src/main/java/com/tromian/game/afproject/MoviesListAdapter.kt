package com.tromian.game.afproject

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesListAdapter(
        context: Context,
        val films: List<Movie>
) : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun getItem(position: Int): Movie = films[position]

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bgPoster: ImageView = itemView.findViewById(R.id.ivBackgroundPoster)
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val legalAge: TextView = itemView.findViewById(R.id.tvAge)
        private val category: TextView = itemView.findViewById(R.id.tvTag)
        private val reviews: TextView = itemView.findViewById(R.id.tvReviews)
        private val runtime: TextView = itemView.findViewById(R.id.tvRuntime)

        private val star1: ImageView = itemView.findViewById(R.id.star1)
        private val star2: ImageView = itemView.findViewById(R.id.star2)
        private val star3: ImageView = itemView.findViewById(R.id.star3)
        private val star4: ImageView = itemView.findViewById(R.id.star4)
        private val star5: ImageView = itemView.findViewById(R.id.star5)


        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            bgPoster.setImageResource(movie.titleImage)
            title.text = movie.name
            legalAge.text = movie.age
            category.text = movie.category
            reviews.text = "${movie.reviewers} Reviews"
            runtime.text = "${movie.runtime} MIN"
            showRating(movie.rating)

        }

        fun showRating(rating: Int) = when (rating) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    override fun getItemCount(): Int {
        return films.size
    }


}