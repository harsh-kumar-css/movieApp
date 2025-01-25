package com.example.movieapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.local.MovieEntity
import com.example.movieapp.data.mappers.toMovie
import com.example.movieapp.data.paging.MovieRemoteMediator
import com.example.movieapp.data.remote.ApiService
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: MovieDatabase
): MovieRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getMoviesPagingData(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { database.movieDao.getMovies()}
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieRemoteMediator(apiService,database),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { movieEntity ->
                movieEntity.toMovie() // Convert to domain model
            }
        }
    }

//    fun getMoviesModelPagingData() : Flow<PagingData<Movie>> {
//        return getMoviesPagingData().map {pagingData ->
//            pagingData.map {movieEntity ->
//                movieEntity.toMovie()
//            }
//        }
//    }
}