package com.tromian.game.afproject.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tromian.game.afproject.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView : BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
    }


}