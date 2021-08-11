package com.tromian.game.afproject

import android.app.Application
import android.content.Context

class MoviesApp : Application() {

    private var _appComponent: AppComponent? = null
    internal val appComponent: AppComponent
    get() = checkNotNull(_appComponent) {
        "AppComponent isn't initialized"
    }

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }



}
val Context.appComponent: AppComponent
    get() = when (this) {
        is MoviesApp -> appComponent
        else -> this.applicationContext.appComponent
    }