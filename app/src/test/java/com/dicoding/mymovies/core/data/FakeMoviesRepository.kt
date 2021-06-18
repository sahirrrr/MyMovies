package com.dicoding.mymovies.core.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.mymovies.core.data.source.local.LocalDataSource
import com.dicoding.mymovies.core.data.source.local.entity.*
import com.dicoding.mymovies.core.data.source.remote.ApiResponse
import com.dicoding.mymovies.core.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.core.data.source.remote.response.*
import com.dicoding.mymovies.core.utils.AppExecutors
import com.dicoding.mymovies.vo.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FakeMoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): MoviesDataResource {

    override fun getPopularFilm(): LiveData<Resource<PagedList<PopularFilmEntity>>> {
        return object :
            NetworkBoundResource<List<PopularFilmResults>, PagedList<PopularFilmEntity>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<PopularFilmEntity>> {
                val page = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllPopularFilm(), page).build()
            }

            override fun shouldFetch(data: PagedList<PopularFilmEntity>?): Boolean {
                return true
            }

            override fun saveCallResult(response: List<PopularFilmResults>) {
                val filmList = ArrayList<PopularFilmEntity>()
                for (res in response) {
                    val film = PopularFilmEntity(
                        res.id,
                        res.title,
                        res.releaseDate,
                        res.posterPath,
                        res.voteAverage
                    )
                    filmList.add(film)
                }
                localDataSource.insertPopularFilm(filmList)
            }

            override fun createCall(): LiveData<ApiResponse<List<PopularFilmResults>>> {
                return remoteDataSource.getPopularFilm()
            }
        }.asLiveData()
    }

    override fun getUpcomingFilm(): LiveData<Resource<PagedList<UpcomingFilmEntity>>> {
        return object :
            NetworkBoundResource<List<UpcomingFilmResults>, PagedList<UpcomingFilmEntity>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<UpcomingFilmEntity>> {
                val page = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllUpcomingFilm(), page).build()
            }

            override fun shouldFetch(data: PagedList<UpcomingFilmEntity>?): Boolean {
                return true
            }

            override fun saveCallResult(response: List<UpcomingFilmResults>) {
                val filmList = ArrayList<UpcomingFilmEntity>()
                for (res in response) {
                    val film = UpcomingFilmEntity(
                        res.id,
                        res.title,
                        res.backdropPath
                    )
                    filmList.add(film)
                }
                localDataSource.insertUpcomingFilm(filmList)
            }

            override fun createCall(): LiveData<ApiResponse<List<UpcomingFilmResults>>> {
                return remoteDataSource.getUpcomingFilm()
            }
        }.asLiveData()
    }

    override fun getTopRatedSeries(): LiveData<Resource<PagedList<TopRatedSeriesEntity>>> {
        return object :
            NetworkBoundResource<List<TopRatedSeriesResults>, PagedList<TopRatedSeriesEntity>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<TopRatedSeriesEntity>> {
                val page = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTopRatedSeries(), page).build()
            }

            override fun shouldFetch(data: PagedList<TopRatedSeriesEntity>?): Boolean {
                return true
            }

            override fun saveCallResult(response: List<TopRatedSeriesResults>) {
                val seriesList = ArrayList<TopRatedSeriesEntity>()
                for (res in response) {
                    val series = TopRatedSeriesEntity(
                        res.id,
                        res.name,
                        res.firstAirDate,
                        res.posterPath,
                        res.voteAverage
                    )
                    seriesList.add(series)
                }
                localDataSource.insertTopRatedSeries(seriesList)
            }

            override fun createCall(): LiveData<ApiResponse<List<TopRatedSeriesResults>>> {
                return remoteDataSource.getTopRatedSeries()
            }
        }.asLiveData()
    }

    override fun getPopularSeries(): LiveData<Resource<PagedList<PopularSeriesEntity>>> {
        return object :
            NetworkBoundResource<List<PopularSeriesResults>, PagedList<PopularSeriesEntity>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<PopularSeriesEntity>> {
                val page = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllPopularSeries(), page).build()
            }

            override fun shouldFetch(data: PagedList<PopularSeriesEntity>?): Boolean {
                return true
            }

            override fun saveCallResult(response: List<PopularSeriesResults>) {
                val seriesList = ArrayList<PopularSeriesEntity>()
                for (res in response) {
                    val series = PopularSeriesEntity(
                        res.id,
                        res.name,
                        res.firstAirDate,
                        res.posterPath,
                        res.voteAverage
                    )
                    seriesList.add(series)
                }
                localDataSource.insertPopularSeries(seriesList)
            }

            override fun createCall(): LiveData<ApiResponse<List<PopularSeriesResults>>> {
                return remoteDataSource.getPopularSeries()
            }

        }.asLiveData()
    }


    override fun getDetailFilm(filmId: Int): LiveData<Resource<DetailFilmEntity>> {
        return object : NetworkBoundResource<DetailFilmResponse, DetailFilmEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailFilmEntity> {
                return localDataSource.getDetailFilm(filmId)
            }

            override fun shouldFetch(data: DetailFilmEntity?): Boolean {
                return data == null
            }

            override fun saveCallResult(response: DetailFilmResponse) {
                val film = DetailFilmEntity(
                    response.id,
                    response.title,
                    response.originalLanguage,
                    response.releaseDate,
                    response.tagLine,
                    response.overview,
                    response.voteAverage,
                    response.backdropPath,
                    response.posterPath
                )
                localDataSource.insertDetailFilm(film)
            }

            override fun createCall(): LiveData<ApiResponse<DetailFilmResponse>> {
                return remoteDataSource.getDetailFilm(filmId)
            }
        }.asLiveData()
    }

    override fun getDetailSeries(tvId: Int): LiveData<Resource<DetailSeriesEntity>> {
        return object :
            NetworkBoundResource<DetailSeriesResponse, DetailSeriesEntity>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailSeriesEntity> {
                return localDataSource.getDetailSeries(tvId)
            }

            override fun shouldFetch(data: DetailSeriesEntity?): Boolean {
                return data == null
            }

            override fun saveCallResult(response: DetailSeriesResponse) {
                val series = DetailSeriesEntity(
                    response.id,
                    response.name,
                    response.firstAirDate,
                    response.originalLanguage,
                    response.numberOfSeasons,
                    response.numberOfEpisodes,
                    response.voteAverage,
                    response.overview,
                    response.posterPath,
                    response.backdropPath
                )
                localDataSource.insertDetailSeries(series)
            }

            override fun createCall(): LiveData<ApiResponse<DetailSeriesResponse>> {
                return remoteDataSource.getDetailSeries(tvId)
            }
        }.asLiveData()
    }

    override fun getFavoriteFilm(): LiveData<PagedList<DetailFilmEntity>> {
        val page = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(2)
            .setPageSize(2)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteFilm(), page).build()
    }

    override fun insertFavoriteFilm(detailFilm: DetailFilmEntity, isFavorite: Boolean) {
        GlobalScope.launch {
            localDataSource.insertFavoriteFilm(detailFilm, isFavorite)
        }
    }

    override fun getFavoriteSeries(): LiveData<PagedList<DetailSeriesEntity>> {
        val page = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteSeries(), page).build()
    }

    override fun insertFavoriteSeries(detailSeries: DetailSeriesEntity, isFavorite: Boolean) {
        GlobalScope.launch {
            localDataSource.insertFavoriteSeries(detailSeries, isFavorite)
        }
    }
}