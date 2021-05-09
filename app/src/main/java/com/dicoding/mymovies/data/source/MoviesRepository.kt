package com.dicoding.mymovies.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.data.source.remote.RemoteDataSource

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


}