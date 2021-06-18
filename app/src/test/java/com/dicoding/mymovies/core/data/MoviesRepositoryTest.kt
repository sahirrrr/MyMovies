package com.dicoding.mymovies.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.mymovies.core.data.source.local.LocalDataSource
import com.dicoding.mymovies.core.data.source.local.entity.*
import com.dicoding.mymovies.core.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.core.utils.AppExecutors
import com.dicoding.mymovies.core.utils.DataMovies
import com.dicoding.mymovies.core.utils.LiveDataTestUtil
import com.dicoding.mymovies.core.utils.PagedListUtil
import com.dicoding.mymovies.vo.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val moviesRepository = FakeMoviesRepository(remote, local, appExecutors)

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
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, PopularFilmEntity>
        `when`(local.getAllPopularFilm()).thenReturn(dataSourceFactory)
        moviesRepository.getPopularFilm()

        val popularFilmEntities = Resource.success(PagedListUtil.mockPagedList(DataMovies.generateDummyPopularFilm()))
        verify(local).getAllPopularFilm()
        assertNotNull(popularFilmEntities.data)
        assertEquals(popularFilmResponse.size.toLong(), popularFilmEntities.data?.size?.toLong())
    }

    @Test
    fun getUpcomingFilm() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, UpcomingFilmEntity>
        `when`(local.getAllUpcomingFilm()).thenReturn(dataSourceFactory)
        moviesRepository.getUpcomingFilm()

        val upcomingFilmEntities = Resource.success(PagedListUtil.mockPagedList(DataMovies.generateDummyUpcomingFilm()))
        verify(local).getAllUpcomingFilm()
        assertNotNull(upcomingFilmEntities.data)
        assertEquals(upcomingFilmResponse.size.toLong(), upcomingFilmEntities.data?.size?.toLong())
    }

    @Test
    fun getPopularSeries() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, PopularSeriesEntity>
        `when`(local.getAllPopularSeries()).thenReturn(dataSourceFactory)
        moviesRepository.getPopularSeries()

        val popularSeriesEntities = Resource.success(PagedListUtil.mockPagedList(DataMovies.generateDummyPopularSeries()))
        verify(local).getAllPopularSeries()
        assertNotNull(popularSeriesEntities.data)
        assertEquals(popularSeriesResponse.size.toLong(), popularSeriesEntities.data?.size?.toLong())
    }

    @Test
    fun getTopRatedSeries() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TopRatedSeriesEntity>
        `when`(local.getAllTopRatedSeries()).thenReturn(dataSourceFactory)
        moviesRepository.getTopRatedSeries()

        val topRatedSeriesEntities = Resource.success(PagedListUtil.mockPagedList(DataMovies.generateDummyTopRatedSeries()))
        verify(local).getAllTopRatedSeries()
        assertNotNull(topRatedSeriesEntities.data)
        assertEquals(topRatedSeriesResponse.size.toLong(), topRatedSeriesEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailFilm() {
        val dummyDetailFilm = MutableLiveData<DetailFilmEntity>()
        dummyDetailFilm.value = DataMovies.generateDummyDetailFilm()
        `when`(local.getDetailFilm(popularFilmId)).thenReturn(dummyDetailFilm)

        val detailFilmEntities = LiveDataTestUtil.getValue(moviesRepository.getDetailFilm(popularFilmId))
        verify(local).getDetailFilm(popularFilmId)
        assertNotNull(detailFilmEntities)
        assertNotNull(detailFilmEntities.data)
        assertNotNull(detailFilmEntities.data?.id)
        assertEquals(detailFilmResponse.id, detailFilmEntities.data?.id)
    }

    @Test
    fun getDetailSeries() {
        val dummyDetailSeries = MutableLiveData<DetailSeriesEntity>()
        dummyDetailSeries.value = DataMovies.generateDummyDetailSeries()
        `when`(local.getDetailSeries(popularSeriesId)).thenReturn(dummyDetailSeries)


        val detailSeriesEntities = LiveDataTestUtil.getValue(moviesRepository.getDetailSeries(popularSeriesId))
        verify(local).getDetailSeries(popularSeriesId)
        assertNotNull(detailSeriesEntities)
        assertNotNull(detailSeriesEntities.data)
        assertNotNull(detailSeriesEntities.data?.id)
        assertEquals(detailSeriesResponse.id, detailSeriesEntities.data?.id)
    }
}