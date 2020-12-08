package com.tromian.game.afproject


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment(R.layout.fragment_movie_details) {
    private var someFragmentClickListener : SomeItemClickListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<TextView>(R.id.tvBack).apply {
            setOnClickListener {
                someFragmentClickListener?.onBackButtonClicked()
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SomeItemClickListener){
            someFragmentClickListener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        someFragmentClickListener = null
    }

}