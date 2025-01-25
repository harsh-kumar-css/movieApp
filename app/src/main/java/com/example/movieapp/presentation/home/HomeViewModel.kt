package com.example.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.mappers.toMovie
import com.example.movieapp.data.mappers.toMovieEntity
import com.example.movieapp.data.paging.MovieRemoteMediator
import com.example.movieapp.data.remote.ApiService
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieApi: ApiService,
    private val movieDatabase: MovieDatabase
) : ViewModel() {

    val getMovies = movieRepository.getMoviesPagingData()

//    val _movies = MutableStateFlow<List<Movie>>(emptyList())
//    var movies : StateFlow<List<Movie>> = _movies.asStateFlow()

//    fun getMovies() = viewModelScope.launch {
//       val response =  movieApi.getMovies(page = 1)
//        val movies = response.movies?.map { it.toMovie() }
//        _movies.value = movies ?: listOf()
//    }

//    @OptIn(ExperimentalPagingApi::class)
//    val moviePager = Pager(
//        config = PagingConfig(
//            pageSize = 20, // Define how many items per page
//            enablePlaceholders = false,
//            initialLoadSize = 40 // Initial number of items to load
//        ),
//        remoteMediator = MovieRemoteMediator(movieApi, movieDatabase), // Use the RemoteMediator
//        pagingSourceFactory = { movieDatabase.movieDao.getMovies() } // Room PagingSource for local cache
//    ).flow.map {pagingData ->
//        pagingData.map {movieEntity ->
//            movieEntity.toMovie()
//        }
//    }.cachedIn(viewModelScope)

}