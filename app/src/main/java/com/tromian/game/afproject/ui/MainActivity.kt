package com.tromian.game.afproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tromian.game.afproject.R
import com.tromian.game.afproject.SomeItemClickListener
import com.tromian.game.afproject.model.repository.MoviesRepository
import com.tromian.game.afproject.ui.fragments.FragmentMoviesList
import com.tromian.game.afproject.viewmodels.MovieDetailsViewModel
import com.tromian.game.afproject.viewmodels.MoviesViewModel
import com.tromian.game.afproject.viewmodels.MoviesViewModelProviderFactory

class MainActivity : AppCompatActivity(), SomeItemClickListener {

    companion object {
        const val FRAGMENT_TAG = "List"
    }

    lateinit var moviesViewModel: MoviesViewModel
    lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = MoviesRepository()
        val viewModelFactory = MoviesViewModelProviderFactory(application,repository)
        moviesViewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, FragmentMoviesList(), FRAGMENT_TAG)
                    .commit()

        } else {
            supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as FragmentMoviesList
        }

    }

    override fun onBackButtonClicked() {
        supportFragmentManager.popBackStack()
    }


}