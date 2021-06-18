package com.dicoding.mymovies.core.data

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.dicoding.mymovies.core.data.source.local.LocalDataSource
import com.dicoding.mymovies.core.data.source.remote.ApiResponse
import com.dicoding.mymovies.core.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.core.data.source.remote.response.*
import com.dicoding.mymovies.core.domain.IMoviesRepository
import com.dicoding.mymovies.core.domain.model.*
import com.dicoding.mymovies.core.utils.AppExecutors
import com.dicoding.mymovies.core.utils.DataMapper
import com.dicoding.mymovies.vo.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMoviesRepository {

    override fun getPopularFilm(): Flowable<Resource<PagedList<PopularFilmModel>>> {
        return object : NetworkBoundResource<List<PopularFilmResults>, PagedList<PopularFilmModel>>(appExecutors) {
            override fun loadFromDB(): Flowable<PagedList<PopularFilmModel>> {
                val page = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return RxPagedListBuilder(localDataSource.getAllPopularFilm().map {
                    DataMapper.mapPopularFilmEntitiesToDomain(it)
                  }, page).buildFlowable(BackpressureStrategy.BUFFER)
            }

            override fun shouldFetch(data: PagedList<PopularFilmModel>?): Boolean {
                return true
            }

            override fun saveCallResult(response: List<PopularFilmResults>) {
                val popularFilmList = DataMapper.mapPopularFilmToEntity(response)
                localDataSource.insertPopularFilm(popularFilmList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<PopularFilmResults>>> {
                return remoteDataSource.getPopularFilm()
            }
        }.asFlowable()
    }

    override fun getUpcomingFilm(): Flowable<Resource<PagedList<UpcomingFilmModel>>> {
        return object : NetworkBoundResource<List<UpcomingFilmResults>, PagedList<UpcomingFilmModel>>(appExecutors) {
            override fun loadFromDB(): Flowable<PagedList<UpcomingFilmModel>> {
                val page = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return RxPagedListBuilder(localDataSource.getAllUpcomingFilm().map {
                   DataMapper.mapUpcomingFilmEntitiesToDomain(it)
                }, page).buildFlowable(BackpressureStrategy.BUFFER)
            }

            override fun shouldFetch(data: PagedList<UpcomingFilmModel>?): Boolean {
                return true
            }

            override fun saveCallResult(response: List<UpcomingFilmResults>) {
                val upcomingFilmList = DataMapper.mapUpcomingFilmToEntity(response)
                localDataSource.insertUpcomingFilm(upcomingFilmList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<UpcomingFilmResults>>> {
                return remoteDataSource.getUpcomingFilm()
            }
        }.asFlowable()
    }

    override fun getTopRatedSeries(): Flowable<Resource<PagedList<TopRatedSeriesModel>>> {
        return object : NetworkBoundResource<List<TopRatedSeriesResults>, PagedList<TopRatedSeriesModel>>(appExecutors) {
            override fun loadFromDB(): Flowable<PagedList<TopRatedSeriesModel>> {
                val page = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return RxPagedListBuilder(localDataSource.getAllTopRatedSeries().map {
                   DataMapper.mapTopRatedSeriesEntitiesToDomain(it)
                }, page).buildFlowable(BackpressureStrategy.BUFFER)
            }

            override fun shouldFetch(data: PagedList<TopRatedSeriesModel>?): Boolean {
                return true
            }

            override fun saveCallResult(response: List<TopRatedSeriesResults>) {
                val topRatedSeriesList = DataMapper.mapTopRatedSeriesToEntity(response)
                localDataSource.insertTopRatedSeries(topRatedSeriesList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<List<TopRatedSeriesResults>>> {
                return remoteDataSource.getTopRatedSeries()
            }
        }.asFlowable()
    }

    override fun getPopularSeries(): Flowable<Resource<PagedList<PopularSeriesModel>>> {
        return object : NetworkBoundResource<List<PopularSeriesResults>, PagedList<PopularSeriesModel>>(appExecutors) {
            override fun loadFromDB(): Flowable<PagedList<PopularSeriesModel>> {
                val page = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return RxPagedListBuilder(localDataSource.getAllPopularSeries().map {
                    DataMapper.mapPopularSeriesEntitiesToDomain(it)
                }, page).buildFlowable(BackpressureStrategy.BUFFER)
            }

            override fun shouldFetch(data: PagedList<PopularSeriesModel>?): Boolean {
                return true
            }

            override fun saveCallResult(response: List<PopularSeriesResults>) {
                val popularSeriesList = DataMapper.mapPopularSeriesToEntity(response)
                localDataSource.insertPopularSeries(popularSeriesList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()

            }

            override fun createCall(): Flowable<ApiResponse<List<PopularSeriesResults>>> {
                return remoteDataSource.getPopularSeries()
            }
        }.asFlowable()
    }



    override fun getDetailFilm(filmId: Int): Flowable<Resource<DetailFilmModel>> {
        return object : NetworkBoundResource<DetailFilmResponse, DetailFilmModel>(appExecutors) {
            override fun loadFromDB(): Flowable<DetailFilmModel> {
                return localDataSource.getDetailFilm(filmId).map { DataMapper.mapDetailFilmEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: DetailFilmModel?): Boolean {
                return true
            }

            override fun saveCallResult(response: DetailFilmResponse) {
                val detailFilm = DataMapper.mapDetailFilmToEntity(response)
                localDataSource.insertDetailFilm(detailFilm)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
            override fun createCall(): Flowable<ApiResponse<DetailFilmResponse>> {
                return remoteDataSource.getDetailFilm(filmId)
            }
        }.asFlowable()
    }

    override fun getDetailSeries(tvId: Int): Flowable<Resource<DetailSeriesModel>> {
        return object : NetworkBoundResource<DetailSeriesResponse, DetailSeriesModel>(appExecutors) {
            override fun loadFromDB(): Flowable<DetailSeriesModel> {
                return localDataSource.getDetailSeries(tvId).map { DataMapper.mapDetailSeriesEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: DetailSeriesModel?): Boolean {
                return true
            }

            override fun saveCallResult(response: DetailSeriesResponse) {
                val detailSeries = DataMapper.mapDetailSeriesToEntity(response)
                localDataSource.insertDetailSeries(detailSeries)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<DetailSeriesResponse>> {
                return remoteDataSource.getDetailSeries(tvId)
            }
        }.asFlowable()
    }

    override fun getFavoriteFilm(): Flowable<PagedList<DetailFilmModel>> {
        val page = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(2)
            .setPageSize(2)
            .build()
        return RxPagedListBuilder(localDataSource.getAllFavoriteFilm().map {
           DataMapper.mapDetailFilmEntitiesToDomain(it)
        }, page).buildFlowable(BackpressureStrategy.BUFFER)
    }

    override fun insertFavoriteFilm(detailFilm: DetailFilmModel, isFavorite: Boolean) {
        val favFilm = DataMapper.mapDetailFilmDomainToEntities(detailFilm)
        appExecutors.diskIO().execute {
            localDataSource.insertFavoriteFilm(favFilm, isFavorite)
        }
    }

    override fun getFavoriteSeries(): Flowable<PagedList<DetailSeriesModel>> {
        val page = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return RxPagedListBuilder(localDataSource.getAllFavoriteSeries().map {
            DataMapper.mapDetailSeriesEntitiesToDomain(it)
        }, page).buildFlowable(BackpressureStrategy.BUFFER)
    }

    override fun insertFavoriteSeries(detailSeries: DetailSeriesModel, isFavorite: Boolean) {
        val favSeries = DataMapper.mapDetailSeriesDomainToEntities(detailSeries)
        appExecutors.diskIO().execute {
            localDataSource.insertFavoriteSeries(favSeries, isFavorite)
        }
    }
}