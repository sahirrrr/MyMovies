package com.dicoding.mymovies.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.data.source.local.room.MoviesDao

class LocalDataSource private constructor(private val moviesDao: MoviesDao) {
    companion object {
        private val instance: LocalDataSource? = null
        fun getInstance(moviesDao: MoviesDao): LocalDataSource =
            instance ?: LocalDataSource(moviesDao)
    }

    // Popular Film
    fun getAllPopularFilm(): DataSource.Factory<Int, PopularFilmEntity> = moviesDao.getAllPopularFilm()

    fun getSelectedPopularFilm(id: Int) : LiveData<PopularFilmEntity> = moviesDao.getSelectedPopularFilm(id)

//    @Query("UPDATE popular_film_table  ")

    fun insertPopularFilm(popularFilm: List<PopularFilmEntity>) = moviesDao.insertPopularFilm(popularFilm)


    // Popular Series
    fun getAllPopularSeries(): DataSource.Factory<Int, PopularSeriesEntity> = moviesDao.getAllPopularSeries()

    fun getSelectedPopularSeries(id: Int) : LiveData<PopularSeriesEntity> = moviesDao.getSelectedPopularSeries(id)

//    @Query("UPDATE popular_series_table  ")

    fun insertPopularSeries(popularSeries: List<PopularSeriesEntity>) = moviesDao.insertPopularSeries(popularSeries)


    // Top Rated Series
    fun getAllTopRatedSeries(): DataSource.Factory<Int, TopRatedSeriesEntity> = moviesDao.getAllTopRatedSeries()

    fun getSelectedTopRatedSeries(id: Int) : LiveData<TopRatedSeriesEntity> = moviesDao.getSelectedTopRatedSeries(id)

//    @Query("UPDATE top_rated_series_table  ")

    fun insertTopRatedSeries(topRatedSeries: TopRatedSeriesEntity) = moviesDao.insertTopRatedSeries(topRatedSeries)


    // Upcoming Film
    fun getAllUpcomingFilm(): DataSource.Factory<Int, UpcomingFilmEntity> = moviesDao.getAllUpcomingFilm()

    fun getSelectedUpcomingFilm(id: Int) : LiveData<UpcomingFilmEntity> = moviesDao.getSelectedUpcomingFilm(id)

//    @Query("UPDATE upcoming_film_table  ")

    fun insertUpcomingFilm(upcomingFilm: List<UpcomingFilmEntity>) = moviesDao.insertUpcomingFilm(upcomingFilm)


    //Favorite
    fun getAllFavoritePopularFilm() : DataSource.Factory<Int, PopularFilmEntity> = moviesDao.getAllFavoritePopularFilm()

    fun insertFavoritePopularFilm(popularFilm: PopularFilmEntity, isFavorite : Boolean) {
        popularFilm.favorite = isFavorite
        moviesDao.insertFavoritePopularFilm(popularFilm)
    }



    fun getAllFavoritePopularSeries() : DataSource.Factory<Int, PopularSeriesEntity> = moviesDao.getAllFavoritePopularSeries()

    fun insertFavoritePopularSeries(popularSeries: PopularSeriesEntity, isFavorite : Boolean) {
        popularSeries.favorite = isFavorite
        moviesDao.insertFavoritePopularSeries(popularSeries)
    }



    fun getAllFavoriteTopRatedSeries() : DataSource.Factory<Int, TopRatedSeriesEntity> = moviesDao.getAllFavoriteTopRatedSeries()

    fun insertFavoriteTopRatedSeries(topRatedSeries: TopRatedSeriesEntity, isFavorite : Boolean) {
        topRatedSeries.favorite = isFavorite
        moviesDao.insertFavoriteTopRatedSeries(topRatedSeries)
    }



    fun getAllFavoriteUpcomingFilm() : DataSource.Factory<Int, UpcomingFilmEntity> = moviesDao.getAllFavoriteUpcomingFilm()

    fun insertFavoriteUpcomingFilm(upcomingFilm: UpcomingFilmEntity, isFavorite : Boolean) {
        upcomingFilm.favorite = isFavorite
        moviesDao.insertFavoriteUpcomingFilm(upcomingFilm)
    }

}