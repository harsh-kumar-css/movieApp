package com.example.movieapp.presentation.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.presentation.home.HomeViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {

//    LaunchedEffect(key1 = Unit) {
//        viewModel.getMovies()
//    }

//    val list by viewModel.movies.collectAsStateWithLifecycle()

    val movieList = viewModel.getMovies.collectAsLazyPagingItems()
    Box(modifier = modifier.fillMaxSize()){
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(movieList){movie->
                MovieItem(movie = movie)
            }
        }
//        LazyColumn {
//            items(list){it->
//                MovieItem(movie = it)
//            }
//        }
//        Button(onClick = {
//            viewModel.getMovies()
//        }) {
//            Text("button")
//        }
    }

}

@Composable
fun MovieItem(modifier: Modifier = Modifier,movie : Movie?) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(color = Color.Cyan)
    ) {
        movie?.run{
            Text(text = id.toString())
            Text(text = backdropPath.toString())
            Text(text = adult.toString())
        }
    }

}