package com.tromian.game.afproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.tromian.game.afproject.model.Movie


class MovieListAdapter(
    val context: Context
) : ListAdapter<Movie, MovieViewHolder>(DIFF_CALLBACK) {
    val TAG = "MyTag"

    init {
        Log.d(TAG, "MovieListAdapter created")
    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.view_holder_movie, parent,false))

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        val movie = differ.currentList[position]
        holder.bind(movie)
        Glide.with(context).load(movie.imageUrl).into(holder.bgPoster)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}