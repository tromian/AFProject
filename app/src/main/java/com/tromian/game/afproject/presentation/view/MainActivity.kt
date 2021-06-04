package com.tromian.game.afproject.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tromian.game.afproject.R
import com.tromian.game.afproject.SomeItemClickListener
import com.tromian.game.afproject.data.repository.MoviesDataRepository
import com.tromian.game.afproject.presentation.viewmodels.MovieDetailsViewModel
import com.tromian.game.afproject.presentation.viewmodels.MoviesViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main), SomeItemClickListener {


    lateinit var moviesViewModel: MoviesViewModel
    lateinit var repository: MoviesDataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repository = MoviesDataRepository()
        moviesViewModel = MoviesViewModel(repository)


    }

    override fun onBackButtonClicked() {
        supportFragmentManager.popBackStack()
    }


}