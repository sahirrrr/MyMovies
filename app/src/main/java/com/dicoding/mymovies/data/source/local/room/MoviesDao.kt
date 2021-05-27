package com.dicoding.mymovies.data.source.local.room

import androidx.lifecycle.LiveData
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity

interface MoviesDao {
    // Popular Film
    @Query("SELECT * FROM popular_film_table")
    fun getAllPopularFilm(): DataSource.Factory<Int, PopularFilmEntity>

    @Query("SELECT * FROM popular_film_table where id = :id")
    fun getSelectedPopularFilm(id: Int) : LiveData<PopularFilmEntity>

//    @Query("UPDATE popular_film_table  ")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularFilm(popularFilm: List<PopularFilmEntity>)


    // Popular Series
    @Query("SELECT * FROM popular_series_table")
    fun getAllPopularSeries(): DataSource.Factory<Int, PopularSeriesEntity>

    @Query("SELECT * FROM popular_series_table where id = :id")
    fun getSelectedPopularSeries(id: Int) : LiveData<PopularSeriesEntity>

//    @Query("UPDATE popular_series_table  ")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularSeries(popularSeries: List<PopularSeriesEntity>)


    // Top Rated Series
    @Query("SELECT * FROM top_rated_series_table")
    fun getAllTopRatedSeries(): DataSource.Factory<Int, TopRatedSeriesEntity>

    @Query("SELECT * FROM top_rated_series_table where id = :id")
    fun getSelectedTopRatedSeries(id: Int) : LiveData<TopRatedSeriesEntity>

//    @Query("UPDATE top_rated_series_table  ")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedSeries(topRatedSeries: TopRatedSeriesEntity)


    // Upcoming Film
    @Query("SELECT * FROM upcoming_film_table")
    fun getAllUpcomingFilm(): DataSource.Factory<Int, UpcomingFilmEntity>

    @Query("SELECT * FROM upcoming_film_table where id = :id")
    fun getSelectedUpcomingFilm(id: Int) : LiveData<UpcomingFilmEntity>

//    @Query("UPDATE upcoming_film_table  ")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingFilm(upcomingFilm: List<UpcomingFilmEntity>)


    //Favorite
    @Query("SELECT * FROM popular_film_table where favorite = :favoritePopularFilm")
    fun getAllFavoritePopularFilm(favoritePopularFilm : Boolean = true) : DataSource.Factory<Int, PopularFilmEntity>

//    @Query("SELECT count(id) and count(favorite) from popular_film_table where id = :id")
//    fun checkFavoritePopularFilm(id: Int) : Int

    @Update
    fun insertFavoritePopularFilm(popularFilm: PopularFilmEntity)

//    @Delete
//    fun deleteFavoritePopularFilm(popularFilm: PopularFilmEntity)



    @Query("SELECT * FROM popular_series_table where favorite = :favoritePopularSeries")
    fun getAllFavoritePopularSeries(favoritePopularSeries : Boolean = true) : DataSource.Factory<Int, PopularSeriesEntity>

//    @Query("SELECT count(id) and count(favorite) from popular_series_table where id = :id")
//    fun checkFavoritePopularSeries(id: Int) : Int

    @Update
    fun insertFavoritePopularSeries(popularSeries: PopularSeriesEntity)

//    @Delete
//    fun deleteFavoritePopularSeries(popularSeries: PopularSeriesEntity)



    @Query("SELECT * FROM top_rated_series_table where favorite = :favoriteTopRatedSeries")
    fun getAllFavoriteTopRatedSeries(favoriteTopRatedSeries : Boolean = true) : DataSource.Factory<Int, TopRatedSeriesEntity>

//    @Query("SELECT count(id) and count(favorite) from top_rated_series_table where id = :id")
//    fun checkFavoriteTopRatedSeries(id: Int) : Int

    @Update
    fun insertFavoriteTopRatedSeries(topRatedSeries: TopRatedSeriesEntity)

//    @Delete
//    fun deleteFavoriteTopRatedSeries(topRatedSeries: TopRatedSeriesEntity)



    @Query("SELECT * FROM upcoming_film_table where favorite = :favoriteUpcomingFilm")
    fun getAllFavoriteUpcomingFilm(favoriteUpcomingFilm : Boolean = true) : DataSource.Factory<Int, UpcomingFilmEntity>

//    @Query("SELECT count(id) and count(favorite) from upcoming_film_table where id = :id")
//    fun checkFavoriteUpcomingFilm(id: Int) : Int

    @Update
    fun insertFavoriteUpcomingFilm(upcomingFilm: UpcomingFilmEntity)

//    @Delete
//    fun deleteFavoriteUpcomingFilm(upcomingFilm: UpcomingFilmEntity)
}