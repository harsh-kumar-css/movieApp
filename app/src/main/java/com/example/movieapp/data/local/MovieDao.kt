package com.example.movieapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies : List<MovieEntity>)

    @Query("select * from movies_table")
    fun getMovies() : PagingSource<Int,MovieEntity>

    @Query("delete from movies_table")
    suspend fun deleteMovies()
}