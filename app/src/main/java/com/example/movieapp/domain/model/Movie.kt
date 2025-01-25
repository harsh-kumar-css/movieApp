package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
)