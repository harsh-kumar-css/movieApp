package com.example.movieapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDto(
//    val dates: Dates,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val movies: List<MovieDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)