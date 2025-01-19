package com.example.movieapp.data.mappers

import com.example.movieapp.data.local.MovieEntity
import com.example.movieapp.data.remote.dto.MovieDto
import com.example.movieapp.domain.model.Movie


fun MovieDto.toMovieEntity(page : Int?) : MovieEntity {
    return MovieEntity(
        movieId = id ?: 1 ,
        backdropPath = backdropPath ?: "",
        adult = adult ?: false,
        page = page ?: 1
    )
}

fun MovieEntity.toMovie() : Movie {
    return Movie(
        id = movieId ,
        backdropPath = backdropPath,
        adult = adult
    )
}

fun MovieDto.toMovie() : Movie {
    return Movie(
        id = id ?: 1,
        backdropPath = backdropPath ?: "",
        adult = adult ?: false
    )
}

