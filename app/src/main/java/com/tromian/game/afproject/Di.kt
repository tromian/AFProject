package com.tromian.game.afproject

import android.app.Application
import com.tromian.game.afproject.data.repository.MoviesDataRepository
import com.tromian.game.afproject.domain.repository.MoviesRepository

object Di {
    val moviesRepo : MoviesRepository by lazy {
        MoviesDataRepository(MoviesApp.context)
    }
}