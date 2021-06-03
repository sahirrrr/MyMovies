package com.dicoding.mymovies.di

import android.content.Context
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.local.LocalDataSource
import com.dicoding.mymovies.data.source.local.room.MyMoviesDatabase
import com.dicoding.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MoviesRepository {
        val database = MyMoviesDatabase.getDBInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val appExecutors = AppExecutors()
        val localDataSource = LocalDataSource.getInstance(database.MoviesDao())
        return MoviesRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}