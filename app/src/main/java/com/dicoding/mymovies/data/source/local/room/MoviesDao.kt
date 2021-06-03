package com.dicoding.mymovies.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.mymovies.data.source.local.entity.*

@Dao
interface MoviesDao {
    // Popular Film
    @Query("SELECT * FROM popular_film_table")
    fun getAllPopularFilm(): DataSource.Factory<Int, PopularFilmEntity>

    @Query("SELECT * FROM popular_film_table where id = :id")
    fun getSelectedPopularFilm(id: Int) : LiveData<PopularFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularFilm(popularFilm: List<PopularFilmEntity>)


    // Popular Series
    @Query("SELECT * FROM popular_series_table")
    fun getAllPopularSeries(): DataSource.Factory<Int, PopularSeriesEntity>

    @Query("SELECT * FROM popular_series_table where id = :id")
    fun getSelectedPopularSeries(id: Int) : LiveData<PopularSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularSeries(popularSeries: List<PopularSeriesEntity>)


    // Top Rated Series
    @Query("SELECT * FROM top_rated_series_table")
    fun getAllTopRatedSeries(): DataSource.Factory<Int, TopRatedSeriesEntity>

    @Query("SELECT * FROM top_rated_series_table where id = :id")
    fun getSelectedTopRatedSeries(id: Int) : LiveData<TopRatedSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedSeries(topRatedSeries: List<TopRatedSeriesEntity>)


    // Upcoming Film
    @Query("SELECT * FROM upcoming_film_table")
    fun getAllUpcomingFilm(): DataSource.Factory<Int, UpcomingFilmEntity>

    @Query("SELECT * FROM upcoming_film_table where id = :id")
    fun getSelectedUpcomingFilm(id: Int) : LiveData<UpcomingFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingFilm(upcomingFilm: List<UpcomingFilmEntity>)


    //Detail
    @Query("SELECT * FROM detail_film_table where id = :id")
    fun getDetailFilm(id: Int): LiveData<DetailFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailFilm(detailFilm: DetailFilmEntity)

    @Query("SELECT * FROM detail_series_table where id = :id")
    fun getDetailSeries(id: Int): LiveData<DetailSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailSeries(detailSeries: DetailSeriesEntity)


    //Favorite Film
    @Query("SELECT * FROM detail_film_table where favorite = :favoriteFilm")
    fun getAllFavoriteFilm(favoriteFilm : Boolean = true) : DataSource.Factory<Int, DetailFilmEntity>

    @Update
    suspend fun insertFavoriteFilm(film: DetailFilmEntity)

    //Favorite Series
    @Query("SELECT * FROM detail_series_table where favorite = :favoriteSeries")
    fun getAllFavoriteSeries(favoriteSeries : Boolean = true) : DataSource.Factory<Int, DetailSeriesEntity>

    @Update
    suspend fun insertFavoriteSeries(series: DetailSeriesEntity)
}