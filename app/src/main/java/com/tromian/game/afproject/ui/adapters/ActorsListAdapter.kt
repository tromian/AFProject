package com.tromian.game.afproject.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tromian.game.afproject.R
import com.tromian.game.afproject.model.models.Actor

class ActorsListAdapter(private val context: Context) :
        ListAdapter<Actor, ActorsListAdapter.ActorsViewHolder>(DIFF_CALLBACK) {


    companion object {
        const val TAG = "Tag"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Actor>() {
            override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                    oldItem == newItem
        }
    }


    inner class ActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val avatar: ImageView = itemView.findViewById(R.id.avatar)
        private val name: TextView = itemView.findViewById(R.id.name)

        init {
            Log.d(TAG, "ActorsViewHolder created")
        }

        fun bind(actor: Actor) {
            Log.d(TAG, "ActorsViewHolder bind")

            Glide.with(itemView.context)
                    .load(actor.imageUrl)
                    .error(R.drawable.person_placeholder)
                    .into(avatar)

            name.text = actor.name
            Log.d(TAG, actor.name.toString())
        }
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ActorsViewHolder {
        Log.d(TAG, "ActorsViewHolder ONcreate")
        val inflater: LayoutInflater = LayoutInflater.from(context)
        return ActorsViewHolder(inflater.inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}