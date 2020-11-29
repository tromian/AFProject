package com.tromian.game.afproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity(), SomeItemClickListener {

    companion object {
        const val FRAGMENT_TAG = "List"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, FragmentMoviesList(), FRAGMENT_TAG)
                    .commit()

        }else{
            supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as FragmentMoviesList
        }


    }

    override fun onMoviePreviewClicked() {
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .add(R.id.main_container, FragmentMoviesDetails())
                .commit()
    }

    override fun onBackButtonClicked() {
        supportFragmentManager.popBackStack()
    }

}