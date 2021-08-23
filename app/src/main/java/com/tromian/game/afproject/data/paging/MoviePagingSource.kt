package com.tromian.game.afproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tromian.game.afproject.AppConstants.Companion.NETWORK_PAGE_SIZE
import com.tromian.game.afproject.data.network.models.JsonMovie
import com.tromian.game.afproject.data.network.tmdbapi.TmdbAPI
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1

class MoviePagingSource @AssistedInject constructor(
    private val service: TmdbAPI,
    @Assisted("listType") private val listType: TmdbAPI.ListType
) : PagingSource<Int, JsonMovie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JsonMovie> {
        val position = params.key ?: TMDB_STARTING_PAGE_INDEX

        return try {
            val response = service.getMovieListByListType(listType, position)
            val movies: List<JsonMovie>

            if (response.isSuccessful && response.body() != null) {
                movies = response.body()!!.movieList
            } else {
                throw Exception("Failed loading Response body ")
            }

            val nextKey = if (movies.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == TMDB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, JsonMovie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("listType") listType: TmdbAPI.ListType): MoviePagingSource
    }

}