package com.tromian.game.afproject.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tromian.game.afproject.R
import com.tromian.game.afproject.SomeItemClickListener
import com.tromian.game.afproject.data.repository.MoviesDataRepository

class MainActivity : AppCompatActivity(R.layout.activity_main), SomeItemClickListener {


    var repository: MoviesDataRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = MoviesDataRepository(this)
    }

    override fun onBackButtonClicked() {
        supportFragmentManager.popBackStack()
    }

}