package com.tromian.game.afproject.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigationrail.NavigationRailView
import com.tromian.game.afproject.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment

        val railView: NavigationRailView = findViewById(R.id.navigationRailView)
        val navController = navHostFragment.navController
        railView.setupWithNavController(navController)

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        navView.setupWithNavController(navController)


    }

}