package com.dicoding.mymovies.core.data.source.local

import androidx.paging.DataSource
import com.dicoding.mymovies.core.data.source.local.entity.*
import com.dicoding.mymovies.core.data.source.local.room.MoviesDao
import io.reactivex.Flowable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(private val moviesDao: MoviesDao) {

    // Popular Film
    fun getAllPopularFilm(): DataSource.Factory<Int, PopularFilmEntity> = moviesDao.getAllPopularFilm()

    fun insertPopularFilm(popularFilm: List<PopularFilmEntity>) = moviesDao.insertPopularFilm(popularFilm)


    // Popular Series
    fun getAllPopularSeries(): DataSource.Factory<Int, PopularSeriesEntity> = moviesDao.getAllPopularSeries()

    fun insertPopularSeries(popularSeries: List<PopularSeriesEntity>) = moviesDao.insertPopularSeries(popularSeries)


    // Top Rated Series
    fun getAllTopRatedSeries(): DataSource.Factory<Int, TopRatedSeriesEntity> = moviesDao.getAllTopRatedSeries()

    fun insertTopRatedSeries(topRatedSeries: List<TopRatedSeriesEntity>) = moviesDao.insertTopRatedSeries(topRatedSeries)


    // Upcoming Film
    fun getAllUpcomingFilm(): DataSource.Factory<Int, UpcomingFilmEntity> = moviesDao.getAllUpcomingFilm()

    fun insertUpcomingFilm(upcomingFilm: List<UpcomingFilmEntity>) = moviesDao.insertUpcomingFilm(upcomingFilm)


    // Detail
    fun getDetailFilm(id: Int): Flowable<DetailFilmEntity> = moviesDao.getDetailFilm(id)

    fun insertDetailFilm(detailFilm: DetailFilmEntity) = moviesDao.insertDetailFilm(detailFilm)

    fun getDetailSeries(id: Int): Flowable<DetailSeriesEntity> = moviesDao.getDetailSeries(id)

    fun insertDetailSeries(detailSeries: DetailSeriesEntity) = moviesDao.insertDetailSeries(detailSeries)


    //Favorite Film
    fun getAllFavoriteFilm() : DataSource.Factory<Int, DetailFilmEntity> = moviesDao.getAllFavoriteFilm()

    fun insertFavoriteFilm(film: DetailFilmEntity, isFavorite : Boolean) {
        film.favorite = isFavorite
        GlobalScope.launch {
            moviesDao.insertFavoriteFilm(film)
        }
    }

    //Favorite Series
    fun getAllFavoriteSeries() : DataSource.Factory<Int, DetailSeriesEntity> = moviesDao.getAllFavoriteSeries()

    fun insertFavoriteSeries(series: DetailSeriesEntity, isFavorite : Boolean) {
        series.favorite = isFavorite
        GlobalScope.launch {
            moviesDao.insertFavoriteSeries(series)
        }
    }
}