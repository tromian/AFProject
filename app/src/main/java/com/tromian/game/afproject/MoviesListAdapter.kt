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
        var films: List<Movie>
        ) : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun getItem(position: Int): Movie = films[position]

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val bgPoster: ImageView = itemView.findViewById(R.id.ivBackgroundPoster)
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val legalAge: TextView = itemView.findViewById(R.id.tvAge)
        private val category: TextView = itemView.findViewById(R.id.tvTag)
        private val reviews: TextView = itemView.findViewById(R.id.tvReviews)
        private val runtime: TextView = itemView.findViewById(R.id.tvRuntime)


        @SuppressLint("SetTextI18n")
        fun bind(movie : Movie){
            bgPoster.setImageResource(movie.titleImage)
            title.text = movie.name
            legalAge.text = movie.age
            category.text = movie.category
            reviews.text = "${movie.reviewers} Reviews"
            runtime.text = "${movie.runtime} MIN"
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