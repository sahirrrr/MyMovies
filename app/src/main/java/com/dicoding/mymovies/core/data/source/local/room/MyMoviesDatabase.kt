package com.dicoding.mymovies.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.mymovies.core.data.source.local.entity.*

@Database(entities =
[PopularFilmEntity::class,
PopularSeriesEntity::class,
TopRatedSeriesEntity::class,
UpcomingFilmEntity::class,
DetailFilmEntity::class,
DetailSeriesEntity::class],
    version = 2, exportSchema = false)
abstract class MyMoviesDatabase : RoomDatabase() {
    abstract fun MoviesDao() : MoviesDao
}