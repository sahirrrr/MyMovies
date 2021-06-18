package com.dicoding.mymovies.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.mymovies.core.data.source.local.entity.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MoviesDao {
    // Popular Film
    @Query("SELECT * FROM popular_film_table")
    fun getAllPopularFilm(): DataSource.Factory<Int, PopularFilmEntity>

    @Query("SELECT * FROM popular_film_table where id = :id")
    fun getSelectedPopularFilm(id: Int) : Flowable<PopularFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularFilm(popularFilm: List<PopularFilmEntity>) : Completable


    // Popular Series
    @Query("SELECT * FROM popular_series_table")
    fun getAllPopularSeries(): DataSource.Factory<Int, PopularSeriesEntity>

    @Query("SELECT * FROM popular_series_table where id = :id")
    fun getSelectedPopularSeries(id: Int) : Flowable<PopularSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularSeries(popularSeries: List<PopularSeriesEntity>) : Completable


    // Top Rated Series
    @Query("SELECT * FROM top_rated_series_table")
    fun getAllTopRatedSeries(): DataSource.Factory<Int, TopRatedSeriesEntity>

    @Query("SELECT * FROM top_rated_series_table where id = :id")
    fun getSelectedTopRatedSeries(id: Int) : Flowable<TopRatedSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedSeries(topRatedSeries: List<TopRatedSeriesEntity>) : Completable


    // Upcoming Film
    @Query("SELECT * FROM upcoming_film_table")
    fun getAllUpcomingFilm(): DataSource.Factory<Int, UpcomingFilmEntity>

    @Query("SELECT * FROM upcoming_film_table where id = :id")
    fun getSelectedUpcomingFilm(id: Int) : Flowable<UpcomingFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingFilm(upcomingFilm: List<UpcomingFilmEntity>) : Completable


    //Detail
    @Query("SELECT * FROM detail_film_table where id = :id")
    fun getDetailFilm(id: Int): Flowable<DetailFilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailFilm(detailFilm: DetailFilmEntity) : Completable

    @Query("SELECT * FROM detail_series_table where id = :id")
    fun getDetailSeries(id: Int): Flowable<DetailSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailSeries(detailSeries: DetailSeriesEntity) : Completable


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