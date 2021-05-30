package com.tromian.game.afproject.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tromian.game.afproject.R
import com.tromian.game.afproject.SomeItemClickListener
import com.tromian.game.afproject.data.repository.MoviesDataRepository
import com.tromian.game.afproject.presentation.viewmodels.MovieDetailsViewModel
import com.tromian.game.afproject.presentation.viewmodels.MoviesViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main), SomeItemClickListener {

    companion object {
        const val FRAGMENT_LIST = "List"
        const val FRAGMENT_DETAIL = "Detail"

    }

    lateinit var moviesViewModel: MoviesViewModel
    lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = MoviesDataRepository()
        moviesViewModel = MoviesViewModel(repository)
        movieDetailsViewModel = MovieDetailsViewModel(repository)


//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .add(R.id.main_container, FragmentMoviesList(), FRAGMENT_LIST)
//                    .commit()
//
//        } else if (savedInstanceState.containsKey(FRAGMENT_DETAIL)){
//            supportFragmentManager.findFragmentByTag(FRAGMENT_DETAIL) as FragmentMoviesDetails
//        }else{
//            supportFragmentManager.findFragmentByTag(FRAGMENT_LIST) as FragmentMoviesList
//        }

    }

    override fun onBackButtonClicked() {
        supportFragmentManager.popBackStack()
    }


}