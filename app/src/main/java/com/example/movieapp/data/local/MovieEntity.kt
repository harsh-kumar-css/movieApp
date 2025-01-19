package com.example.movieapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.Constants

@Entity(tableName = Constants.MOVIES_TABLE)
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val movieId : Int,
    val page : Int,
    val adult: Boolean,
    val backdropPath: String,
)