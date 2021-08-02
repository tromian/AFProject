package com.tromian.game.afproject.presentation.view.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tromian.game.afproject.Di
import com.tromian.game.afproject.R
import com.tromian.game.afproject.domain.MovieListType
import com.tromian.game.afproject.presentation.view.adapters.MovieListAdapter
import com.tromian.game.afproject.presentation.viewmodels.MoviesViewModel

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private lateinit var viewModel: MoviesViewModel
    private var listType = MovieListType.POPULAR
    lateinit var tv_list_title: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_list_title = view.findViewById(R.id.tv_movie_list_title)
        tv_list_title.setText(selectListTitle(listType))
        val menuImage: ImageView = view.findViewById(R.id.iv_list_type_popup)
        menuImage.setOnClickListener {
            showPopupMenu(menuImage)
        }

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MoviesViewModel(Di.moviesRepo,listType) as T
            }
        }).get(MoviesViewModel::class.java)

        val adapter = MovieListAdapter() { itemId ->
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
            val action = FragmentMoviesListDirections.actionFragmentMoviesListToFragmentMoviesDetails(movie)
            findNavController().navigate(action)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.list_type_popup_menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            menuItemClicked(menuItem)
        }
        popupMenu.show()
    }

    private fun selectListTitle(type: MovieListType) : String{
        return when(type){
            MovieListType.NOW_PLAYING -> getString(R.string.item_now_playing)
            MovieListType.TOP_RATED -> getString(R.string.item_top_rated)
            MovieListType.POPULAR -> getString(R.string.item_popular)
            MovieListType.UPCOMING -> getString(R.string.item_upcoming)
        }
    }

    private fun menuItemClicked(item: MenuItem): Boolean{
        return when (item.itemId) {
            R.id.item_now_playing -> {
                listType = MovieListType.NOW_PLAYING
                tv_list_title.setText(R.string.item_now_playing)
                viewModel.loadMovieList(listType)
                true
            }
            R.id.item_popular -> {
                listType = MovieListType.POPULAR
                tv_list_title.setText(R.string.item_popular)
                viewModel.loadMovieList(listType)
                true
            }
            R.id.item_top_rated -> {
                listType = MovieListType.TOP_RATED
                tv_list_title.setText(R.string.item_top_rated)
                viewModel.loadMovieList(listType)
                true
            }
            R.id.item_upcoming -> {
                listType = MovieListType.UPCOMING
                tv_list_title.setText(R.string.item_upcoming)
                viewModel.loadMovieList(listType)
                true
            }
            else -> false
        }

    }

}