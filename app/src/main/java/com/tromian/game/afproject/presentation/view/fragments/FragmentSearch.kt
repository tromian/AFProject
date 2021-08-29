package com.tromian.game.afproject.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.R
import com.tromian.game.afproject.appComponent
import com.tromian.game.afproject.presentation.view.adapters.MovieListAdapter
import com.tromian.game.afproject.presentation.viewmodels.MovieSearchVM
import com.tromian.game.afproject.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class FragmentSearch : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var factory: ViewModelFactory.Factory

    private val viewModel by viewModels<MovieSearchVM>(){
        factory.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var job: Job? = null

        val editText : EditText = view.findViewById(R.id.editText)
        editText.addTextChangedListener { editable ->
            job?.cancel()
            job = CoroutineScope(Dispatchers.Default).launch {
                delay(1_000)
                editable?.let {
                    if (editable.isNotEmpty()){
                        viewModel.searchMovie(editable.toString())
                    }
                }
            }

        }

        val adapter = MovieListAdapter(){ itemId ->
            openFragment(itemId)
        }

        viewModel.movieList.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })

        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        rvMovieList.adapter = adapter

    }

    private fun openFragment(itemId: Int) {
        val movie = viewModel.movieList.value?.get(itemId)
        if (movie!=null){
            val action = FragmentSearchDirections.actionFragmentSearchToFragmentMoviesDetails(movie)
            findNavController().navigate(action)
        }
    }
}