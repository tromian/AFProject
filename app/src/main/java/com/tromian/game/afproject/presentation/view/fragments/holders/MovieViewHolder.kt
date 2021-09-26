package com.tromian.game.afproject.presentation.view.fragments.holders


import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.GlideApp
import com.tromian.game.afproject.R
import com.tromian.game.afproject.domain.models.Movie

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val bgPoster: ImageView = itemView.findViewById(R.id.ivBackgroundPoster)
    private val title: TextView = itemView.findViewById(R.id.tvTitle)
    private val legalAge: TextView = itemView.findViewById(R.id.tvAge)
    private val category: TextView = itemView.findViewById(R.id.tvTag)
    private val reviews: TextView = itemView.findViewById(R.id.tvReviewsCount)
    private val liked: ImageView = itemView.findViewById(R.id.liked_item)

    private val star1: ImageView = itemView.findViewById(R.id.star1)
    private val star2: ImageView = itemView.findViewById(R.id.star2)
    private val star3: ImageView = itemView.findViewById(R.id.star3)
    private val star4: ImageView = itemView.findViewById(R.id.star4)
    private val star5: ImageView = itemView.findViewById(R.id.star5)

    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie) {
        title.text = movie.title
        legalAge.text = movie.pgAge.toString() + "+"
        category.text = movie.genres
        reviews.text = "${movie.reviewCount}"
        movie.rating?.let { showRating(it) }
        liked.setImageResource(
            if (movie.isLiked) R.drawable.ic_heart_liked
        else R.drawable.ic_heart)


        GlideApp.with(itemView.context)
                .load(movie.imageUrl)
                .error(R.drawable.film_placeholder)
                .into(bgPoster)

    }


    private fun showRating(rating: Int) = when (rating) {
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