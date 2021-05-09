package com.dicoding.mymovies.di

import android.content.Context
import com.dicoding.mymovies.data.source.MoviesRepository
import com.dicoding.mymovies.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MoviesRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MoviesRepository.getInstance(remoteDataSource)
    }
}