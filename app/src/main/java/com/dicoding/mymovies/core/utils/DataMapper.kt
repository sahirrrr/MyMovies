package com.dicoding.mymovies.core.utils

import com.dicoding.mymovies.core.data.source.local.entity.*
import com.dicoding.mymovies.core.data.source.remote.response.*
import com.dicoding.mymovies.core.domain.model.*

object DataMapper {
    fun mapPopularFilmToEntity(data: List<PopularFilmResults>) : List<PopularFilmEntity> {
        val popularFilmList = ArrayList<PopularFilmEntity>()
        data.map {
            with(it) {
                val popularFilm = PopularFilmEntity(
                    id, title, releaseDate, posterPath, voteAverage
                )
                popularFilmList.add(popularFilm)
            }
        }
        return popularFilmList
    }

    fun mapUpcomingFilmToEntity(data: List<UpcomingFilmResults>) : List<UpcomingFilmEntity> {
        val upcomingFilmList = ArrayList<UpcomingFilmEntity>()
        data.map {
            with(it) {
                val upcomingFilm = UpcomingFilmEntity(
                    id, title, backdropPath
                )
                upcomingFilmList.add(upcomingFilm)
            }
        }
        return upcomingFilmList
    }

    fun mapPopularSeriesToEntity(data: List<PopularSeriesResults>) : List<PopularSeriesEntity> {
        val popularSeriesList = ArrayList<PopularSeriesEntity>()
        data.map {
            with(it) {
                val popularSeries = PopularSeriesEntity(
                    id, name, firstAirDate, posterPath, voteAverage
                )
                popularSeriesList.add(popularSeries)
            }
        }
        return popularSeriesList
    }

    fun mapTopRatedSeriesToEntity(data: List<TopRatedSeriesResults>) : List<TopRatedSeriesEntity> {
        val topRatedSeriesList = ArrayList<TopRatedSeriesEntity>()
        data.map {
            with(it) {
                val topRatedSeries = TopRatedSeriesEntity(
                    id, name, firstAirDate, posterPath, voteAverage
                )
                topRatedSeriesList.add(topRatedSeries)
            }
        }
        return topRatedSeriesList
    }

    fun mapDetailFilmToEntity(data: DetailFilmResponse) : DetailFilmEntity {
        return with(data) {
            DetailFilmEntity(
                id, title, originalLanguage, releaseDate, tagLine, overview, voteAverage, backdropPath, posterPath
            )
        }
    }

    fun mapDetailSeriesToEntity(data: DetailSeriesResponse) : DetailSeriesEntity {
        return with(data) {
            DetailSeriesEntity(
                id, name, firstAirDate, originalLanguage, numberOfSeasons, numberOfEpisodes, voteAverage, overview, posterPath, backdropPath
            )
        }
    }

    fun mapPopularFilmEntitiesToDomain(data: PopularFilmEntity) : PopularFilmModel {
        return with(data) {
            PopularFilmModel(
                id, title, releaseDate, posterPath, voteAverage, favorite
            )
        }
    }

    fun mapUpcomingFilmEntitiesToDomain(data: UpcomingFilmEntity) : UpcomingFilmModel {
        return with(data) {
            UpcomingFilmModel(
                id, title, backdropPath
            )
        }
    }

    fun mapPopularSeriesEntitiesToDomain(data: PopularSeriesEntity) : PopularSeriesModel {
        return with(data) {
            PopularSeriesModel(
                id, name, firstAirDate, posterPath, voteAverage, favorite
            )
        }
    }

    fun mapTopRatedSeriesEntitiesToDomain(data: TopRatedSeriesEntity) : TopRatedSeriesModel {
        return with(data) {
            TopRatedSeriesModel(
                id, name, firstAirDate, posterPath, voteAverage, favorite
            )
        }
    }

    fun mapDetailFilmEntitiesToDomain(data: DetailFilmEntity) : DetailFilmModel {
        return with(data) {
            DetailFilmModel(
                id, title, originalLanguage, releaseDate, tagLine, overview, voteAverage, backdropPath, posterPath, favorite
            )
        }
    }

    fun mapDetailSeriesEntitiesToDomain(data: DetailSeriesEntity) : DetailSeriesModel {
        return with(data) {
            DetailSeriesModel(
                id, name, firstAirDate, originalLanguage, numberOfSeasons, numberOfEpisodes, voteAverage, overview, posterPath, backdropPath, favorite
            )
        }
    }

    fun mapDetailFilmDomainToEntities(data: DetailFilmModel) : DetailFilmEntity {
        return with(data) {
            DetailFilmEntity(
                id, title, originalLanguage, releaseDate, tagLine, overview, voteAverage, backdropPath, posterPath, favorite
            )
        }
    }

    fun mapDetailSeriesDomainToEntities(data: DetailSeriesModel) : DetailSeriesEntity {
        return with(data) {
            DetailSeriesEntity(
                id, name, firstAirDate, originalLanguage, numberOfSeasons, numberOfEpisodes, voteAverage, overview, posterPath, backdropPath, favorite
            )
        }
    }

}