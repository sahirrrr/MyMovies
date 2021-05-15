package com.dicoding.mymovies.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.utils.DataMovies
import com.dicoding.mymovies.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.verify

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val moviesRepository = FakeMoviesRepository(remote)

    private val popularFilmResponse = DataMovies.generateDummyPopularFilm()
    private val popularFilmId = popularFilmResponse[0].id
    private val upcomingFilmResponse = DataMovies.generateDummyUpcomingFilm()
    private val detailFilmResponse = DataMovies.generateDummyDetailFilm()

    private val popularSeriesResponse = DataMovies.generateDummyPopularSeries()
    private val popularSeriesId = popularSeriesResponse[0].id
    private val topRatedSeriesResponse = DataMovies.generateDummyTopRatedSeries()
    private val detailSeriesResponse = DataMovies.generateDummyDetailSeries()

    @Test
    fun getPopularFilm() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularFilmCallback)
                .onPopularFilmReceived(popularFilmResponse)
            null
        }. `when`(remote).getPopularFilm(any())

        val popularFilmEntities = LiveDataTestUtil.getValue(moviesRepository.getPopularFilm())

        verify(remote).getPopularFilm(any())
        assertNotNull(popularFilmEntities)
        assertEquals(popularFilmResponse.size.toLong(), popularFilmEntities.size.toLong())
    }

    @Test
    fun getUpcomingFilm() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadUpcomingFilmCallback)
                    .onUpcomingFilmReceived(upcomingFilmResponse)
            null
        }. `when`(remote).getUpcomingFilm(any())

        val upcomingFilmEntities = LiveDataTestUtil.getValue(moviesRepository.getUpcomingFilm())

        verify(remote).getUpcomingFilm(any())
        assertNotNull(upcomingFilmEntities)
        assertEquals(upcomingFilmResponse.size.toLong(), upcomingFilmEntities.size.toLong())
    }

    @Test
    fun getPopularSeries() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularSeriesCallback)
                    .onPopularSeriesReceived(popularSeriesResponse)
            null
        }. `when`(remote).getPopularSeries(any())

        val popularSeriesEntities = LiveDataTestUtil.getValue(moviesRepository.getPopularSeries())

        verify(remote).getPopularSeries(any())
        assertNotNull(popularSeriesEntities)
        assertEquals(popularSeriesResponse.size.toLong(), popularSeriesEntities.size.toLong())
    }

    @Test
    fun getTopRatedSeries() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTopRatedSeriesCallback)
                    .onTopRatedSeriesReceived(topRatedSeriesResponse)
            null
        }. `when`(remote).getTopRatedSeries(any())

        val topRatedSeriesEntities = LiveDataTestUtil.getValue(moviesRepository.getTopRatedSeries())

        verify(remote).getTopRatedSeries(any())
        assertNotNull(topRatedSeriesEntities)
        assertEquals(topRatedSeriesResponse.size.toLong(), topRatedSeriesEntities.size.toLong())
    }

    @Test
    fun getDetailFilm() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailFilmCallback)
                    .onDetailFilmReceived(detailFilmResponse)
            null
        }. `when`(remote).getDetailFilm(any(), eq(popularFilmId))

        val detailFilmEntities = LiveDataTestUtil.getValue(moviesRepository.getDetailFilm(popularFilmId))

        verify(remote).getDetailFilm(any(), eq(popularFilmId))
        assertNotNull(detailFilmEntities)
        assertEquals(detailFilmResponse.id, detailFilmEntities.id)
    }

    @Test
    fun getDetailSeries() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailSeriesCallback)
                    .onDetailSeriesReceived(detailSeriesResponse)
            null
        }. `when`(remote).getDetailSeries(any(), eq(popularSeriesId))

        val detailSeriesEntities = LiveDataTestUtil.getValue(moviesRepository.getDetailSeries(popularSeriesId))

        verify(remote).getDetailSeries(any(), eq(popularSeriesId))
        assertNotNull(detailSeriesEntities)
        assertEquals(detailSeriesResponse.id, detailSeriesEntities.id)
    }
}