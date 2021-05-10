package com.dicoding.mymovies.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.data.source.remote.response.DetailFilmResponse
import com.dicoding.mymovies.data.source.remote.response.DetailSeriesResponse
import com.dicoding.mymovies.data.source.remote.response.PopularSeriesResponse

class MoviesRepository private constructor(private val remoteDataSource: RemoteDataSource): MoviesDataResource {

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MoviesRepository =
                instance ?: synchronized(this) {
                    MoviesRepository(remoteDataSource).apply { instance = this }
                }
    }

    override fun getPopularFilm(): LiveData<List<PopularFilmEntity>> {
        val filmResult = MutableLiveData<List<PopularFilmEntity>>()
        remoteDataSource.getPopularFilm(object : RemoteDataSource.LoadPopularFilmCallback {
            override fun onPopularFilmReceived(filmResponse: List<PopularFilmEntity>) {
                val filmList = ArrayList<PopularFilmEntity>()
                for (response in filmResponse) {
                    val film = PopularFilmEntity(
                        response.id,
                        response.title,
                        response.releaseDate,
                        response.posterPath,
                        response.voteAverage
                    )
                    filmList.add(film)
                }
                filmResult.postValue(filmList)
            }
        })
        return filmResult
    }

    override fun getUpcomingFilm(): LiveData<List<UpcomingFilmEntity>> {
        val filmResult = MutableLiveData<List<UpcomingFilmEntity>>()
        remoteDataSource.getUpcomingFilm(object : RemoteDataSource.LoadUpcomingFilmCallback {
            override fun onUpcomingFilmReceived(filmResponse: List<UpcomingFilmEntity>) {
                val filmList = ArrayList<UpcomingFilmEntity>()
                for (response in filmResponse) {
                    val film = UpcomingFilmEntity(
                            response.id,
                            response.title,
                            response.backdropPath
                    )
                    filmList.add(film)
                }
                filmResult.postValue(filmList)
            }
        })
        return filmResult
    }

    override fun getTopRatedSeries(): LiveData<List<TopRatedSeriesEntity>> {
        val seriesResult = MutableLiveData<List<TopRatedSeriesEntity>>()
        remoteDataSource.getTopRatedSeries(object : RemoteDataSource.LoadTopRatedSeriesCallback {
            override fun onTopRatedSeriesReceived(seriesResponse: List<TopRatedSeriesEntity>) {
                val seriesList = ArrayList<TopRatedSeriesEntity>()
                for (response in seriesResponse) {
                    val series = TopRatedSeriesEntity(
                            response.id,
                            response.name,
                            response.firstAirDate,
                            response.posterPath,
                            response.voteAverage
                    )
                    seriesList.add(series)
                }
                seriesResult.postValue(seriesList)
            }
        })
        return seriesResult
    }

    override fun getPopularSeries(): LiveData<List<PopularSeriesEntity>> {
        val seriesResult = MutableLiveData<List<PopularSeriesEntity>>()
        remoteDataSource.getPopularSeries(object : RemoteDataSource.LoadPopularSeriesCallback {
            override fun onPopularSeriesReceived(seriesResponse: List<PopularSeriesEntity>) {
                val seriesList = ArrayList<PopularSeriesEntity>()
                for (response in seriesResponse) {
                    val series = PopularSeriesEntity(
                            response.id,
                            response.name,
                            response.firstAirDate,
                            response.posterPath,
                            response.voteAverage
                    )
                    seriesList.add(series)
                }
                seriesResult.postValue(seriesList)
            }
        })
        return seriesResult
    }

    override fun getDetailFilm(moviesId: Int): LiveData<DetailFilmResponse> {
        val filmResult = MutableLiveData<DetailFilmResponse>()
        remoteDataSource.getDetailFilm(object : RemoteDataSource.LoadDetailFilmCallback {
            override fun onDetailFilmReceived(filmResponse: DetailFilmResponse) {
                    val film = DetailFilmResponse(
                        filmResponse.id,
                        filmResponse.title,
                        filmResponse.originalLanguage,
                        filmResponse.releaseDate,
                        filmResponse.tagLine,
                        filmResponse.overview,
                        filmResponse.voteAverage,
                        filmResponse.backdropPath,
                        filmResponse.posterPath
                    )
                filmResult.postValue(film)
            }
        }, moviesId)
        return filmResult
    }

    override fun getDetailSeries(tvId: Int): LiveData<DetailSeriesResponse> {
        val seriesResult = MutableLiveData<DetailSeriesResponse>()
        remoteDataSource.getDetailSeries(object : RemoteDataSource.LoadDetailSeriesCallback {
            override fun onDetailSeriesReceived(seriesResponse: DetailSeriesResponse) {
                val series = DetailSeriesResponse(
                        seriesResponse.id,
                        seriesResponse.name,
                        seriesResponse.firstAirDate,
                        seriesResponse.originalLanguage,
                        seriesResponse.numberOfSeasons,
                        seriesResponse.numberOfEpisodes,
                        seriesResponse.voteAverage,
                        seriesResponse.overview,
                        seriesResponse.posterPath,
                        seriesResponse.backdropPath
                )
                seriesResult.postValue(series)
            }
        }, tvId)
        return seriesResult
    }
}