package com.example.movieapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.local.MovieEntity
import com.example.movieapp.data.mappers.toMovieEntity
import com.example.movieapp.data.remote.ApiService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val movieApi: ApiService,
    private val movieDatabase: MovieDatabase
) : RemoteMediator<Int, MovieEntity>() {

    private val movieDao = movieDatabase.movieDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        try {
            // Page calculation based on LoadType
            val page = when (loadType) {
                LoadType.REFRESH -> 1 // Start from page 1 on refresh
                LoadType.PREPEND -> {
                    // We don't need to load previous pages, so return success if the user tries to load them
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    // Get the last item and calculate the next page number
                    val lastItem = state.lastItemOrNull()
                    // If there is no last item, we are starting from page 1
                    if (lastItem == null) 1
                    else {
                      lastItem.page + 1
                    }
                }
            }

            // Fetch data from the API using the calculated page number

            val movieResponse = movieApi.getMovies(page = page)

            if(movieResponse.isSuccessful) {

                movieResponse.body()?.let { response ->
                    // Perform the database transaction
                    movieDatabase.withTransaction {
                        // Map API response to MovieEntity, include the page number for pagination
                        val movies = response.movies?.map {
                            it.toMovieEntity(response.page)
                        }

                        // Clear previous data on refresh, else append new data
                        if (loadType == LoadType.REFRESH) {
                            movieDao.deleteMovies()
                        }

                        // Insert new movies into the database
                        movies?.let { movieDao.insertMovies(it) }
                    }

                    // Determine if we have reached the end of pagination
                    val endOfPaginationReached = response.movies?.isEmpty() ?: false

                    return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }
            }
            return MediatorResult.Success(endOfPaginationReached = true)
        } catch (e: Exception) {
            // If an error occurs, return the error result
            return MediatorResult.Error(e)
        }
    }
}
