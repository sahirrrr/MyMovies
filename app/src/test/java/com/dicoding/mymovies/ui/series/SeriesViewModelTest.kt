package com.dicoding.mymovies.ui.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mymovies.core.data.MoviesRepository
import com.dicoding.mymovies.core.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.core.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SeriesViewModelTest {

    private lateinit var viewModel: SeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var popularSeriesObserver: Observer<Resource<PagedList<PopularSeriesEntity>>>

    @Mock
    private lateinit var topRatedSeriesObserver: Observer<Resource<PagedList<TopRatedSeriesEntity>>>

    @Mock
    private lateinit var popularPagedList: PagedList<PopularSeriesEntity>

    @Mock
    private lateinit var topRatedPagedList: PagedList<TopRatedSeriesEntity>

    @Before
    fun setUp() {
        viewModel = SeriesViewModel(moviesRepository)
    }

    @Test
    fun getPopularSeries() {
        val dataPopularSeries = Resource.success(popularPagedList)
        `when`(dataPopularSeries.data?.size).thenReturn(20)
        val series = MutableLiveData<Resource<PagedList<PopularSeriesEntity>>>()
        series.value = dataPopularSeries

        `when`(moviesRepository.getPopularSeries()).thenReturn(series)
        val popularSeriesEntity = viewModel.getPopularSeries().value?.data
        verify(moviesRepository).getPopularSeries()
        assertNotNull(popularSeriesEntity)
        assertEquals(20, popularSeriesEntity?.size)

        viewModel.getPopularSeries().observeForever(popularSeriesObserver)
        verify(popularSeriesObserver).onChanged(dataPopularSeries)
    }

    @Test
    fun getTopRatedSeries() {
        val dataTopRatedSeries = Resource.success(topRatedPagedList)
        `when`(dataTopRatedSeries.data?.size).thenReturn(20)
        val series = MutableLiveData<Resource<PagedList<TopRatedSeriesEntity>>>()
        series.value = dataTopRatedSeries

        `when`(moviesRepository.getTopRatedSeries()).thenReturn(series)
        val topRatedSeriesEntity = viewModel.getTopRatedSeries().value?.data
        verify(moviesRepository).getTopRatedSeries()
        assertNotNull(topRatedSeriesEntity)
        assertEquals(20, topRatedSeriesEntity?.size)

        viewModel.getTopRatedSeries().observeForever(topRatedSeriesObserver)
        verify(topRatedSeriesObserver).onChanged(dataTopRatedSeries)
    }
}