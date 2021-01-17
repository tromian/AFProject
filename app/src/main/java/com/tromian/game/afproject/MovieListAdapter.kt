package com.tromian.game.afproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.tromian.game.afproject.model.Movie
import java.lang.Exception


class MovieListAdapter : ListAdapter<Movie, MovieViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.view_holder_movie, parent,false))

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = getItem(position)
        holder.itemView.apply {
            try {
                Glide.with(this)
                        .load(movie.imageUrl)
                        .into(holder.bgPoster)

            }catch (e: Exception){
                Log.d("glide", e.toString())
            }

        }
        holder.bind(movie)

    }



}