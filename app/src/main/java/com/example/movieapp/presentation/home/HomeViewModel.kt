package com.example.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.ConnectivityObserver
import com.example.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    val getMovies = movieRepository.getMoviesPagingData()

    val isConnected = connectivityObserver.isConnected.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )

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