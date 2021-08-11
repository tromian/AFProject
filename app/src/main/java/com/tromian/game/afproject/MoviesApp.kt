package com.tromian.game.afproject

import android.annotation.SuppressLint
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
        _appComponent = DaggerAppComponent.create()
        context = this
    }


    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}
val Context.appComponent: AppComponent
    get() = when (this) {
        is MoviesApp -> appComponent
        else -> this.applicationContext.appComponent
    }