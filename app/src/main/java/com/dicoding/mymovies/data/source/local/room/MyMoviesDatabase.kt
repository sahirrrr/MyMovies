package com.dicoding.mymovies.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity

@Database(entities =
[PopularFilmEntity::class,
PopularSeriesEntity::class,
TopRatedSeriesEntity::class,
UpcomingFilmEntity::class],
    version = 2, exportSchema = false)
abstract class MyMoviesDatabase : RoomDatabase() {
    abstract fun MoviesDao() : MoviesDao
    companion object{
        @Volatile
        private var instance: MyMoviesDatabase? = null

        fun getDBInstance(context: Context) : MyMoviesDatabase{
            if (instance == null){
                synchronized(this){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyMoviesDatabase::class.java,
                        "my_movies.db"
                    ) .build()
                }
            }
            return instance as MyMoviesDatabase
        }
    }
}