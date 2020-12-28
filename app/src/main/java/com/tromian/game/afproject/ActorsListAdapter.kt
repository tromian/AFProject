package com.tromian.game.afproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorsListAdapter(
        context: Context,
        val actors: List<Actor>
) : RecyclerView.Adapter<ActorsListAdapter.ActorsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun getItem(position: Int): Actor = actors[position]

    inner class ActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val avatar: ImageView = itemView.findViewById(R.id.avatar)
        private val name: TextView = itemView.findViewById(R.id.name)

        fun bind(actor: Actor){
            avatar.setImageResource(actor.picture)
            name.text = actor.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsListAdapter.ActorsViewHolder {
        return ActorsViewHolder(inflater.inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsListAdapter.ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return actors.size
    }

}