package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.dto.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = "360b35639aba13b0c36421452aab83a9",
        @Query("page") page: Int
    ) : Response<MovieResponseDto>
}