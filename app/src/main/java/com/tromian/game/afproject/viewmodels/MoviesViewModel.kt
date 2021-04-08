package com.tromian.game.afproject.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.game.afproject.model.data.NowPlaying
import com.tromian.game.afproject.model.models.Movie
import com.tromian.game.afproject.model.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(
       val app : Application,
       val repository : MoviesRepository
) : AndroidViewModel(app) {

    var movieList = MutableLiveData<List<Movie>>()

    init {
        getMovies()
    }


    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        val list = repository.nowPlaying()
        list.enqueue(object : Callback<NowPlaying>{
            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                if (response.isSuccessful){
                    movieList.postValue(
                        response.body()!!.results.map {
                            Movie(
                                title = it.title
                            )
                        }
                    )
                }
            }

            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
               Log.d("MyLog", t.message.toString())
            }

        })

    }

}