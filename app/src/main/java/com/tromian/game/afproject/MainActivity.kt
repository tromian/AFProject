package com.tromian.game.afproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.tromian.game.afproject.data.loadMovies
import com.tromian.game.afproject.model.Movie
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity(), SomeItemClickListener {

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
//        supportFragmentManager.beginTransaction()
//                .addToBackStack(null)
//                .add(R.id.main_container, FragmentMoviesDetails(bundle))
//                .commit()
    }

    override fun onBackButtonClicked() {
        supportFragmentManager.popBackStack()
    }



}