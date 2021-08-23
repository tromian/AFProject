package com.tromian.game.afproject


import android.annotation.SuppressLint
import android.content.Context
import com.tromian.game.afproject.data.db.MoviesDB
import com.tromian.game.afproject.data.network.tmdbapi.TMDBService
import com.tromian.game.afproject.data.network.tmdbapi.TmdbAPI
import com.tromian.game.afproject.data.repository.MoviesDataRepository
import com.tromian.game.afproject.domain.repository.MoviesRepository
import com.tromian.game.afproject.presentation.view.fragments.FragmentFavourite
import com.tromian.game.afproject.presentation.view.fragments.FragmentMoviesDetails
import com.tromian.game.afproject.presentation.view.fragments.FragmentMoviesList
import com.tromian.game.afproject.presentation.view.fragments.FragmentSearch
import dagger.*
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent{

    fun inject(fragment: FragmentMoviesList)
    fun inject(fragment: FragmentMoviesDetails)
    fun inject(fragment: FragmentSearch)
    fun inject(fragment: FragmentFavourite)
    fun inject(repository: MoviesDataRepository)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context) : Builder

        fun build() : AppComponent
    }

}

@SuppressLint("StaticFieldLeak")
@Module(includes = [NetworkModule::class, AppBindModule::class, LocalDBModule::class])
class AppModule {


    @Provides
    fun provideRepoImpl( service : TmdbAPI,
                         localDB: MoviesDB,
                         context: Context
    ) : MoviesDataRepository {
        return MoviesDataRepository(service, localDB, context)
    }
}


@Module
class NetworkModule {

    @Provides
    fun provideTmdbService() : TmdbAPI {
        return TMDBService.tmdbApi
    }
}

@Module
class LocalDBModule {
    @Provides
    @Singleton
    fun provideLocalDB(context: Context): MoviesDB {
        return MoviesDB.getInstance(context)
    }

}

@Module
interface AppBindModule {

    @Binds
    fun bindMoviesRepoImpl_to_MoviesRepo(
        moviesRepoImpl: MoviesDataRepository
    ) : MoviesRepository

}