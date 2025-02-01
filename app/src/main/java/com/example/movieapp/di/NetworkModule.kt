package com.example.movieapp.di

import android.content.Context
import com.example.movieapp.util.AndroidConnectivityObserver
import com.example.core.util.ConnectivityObserver
import com.example.movieapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(@ApplicationContext context : Context) : ConnectivityObserver {
        return AndroidConnectivityObserver(context)
    }
}