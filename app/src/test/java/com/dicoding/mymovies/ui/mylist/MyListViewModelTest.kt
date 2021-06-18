package com.dicoding.mymovies.ui.mylist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mymovies.core.data.MoviesRepository
import com.dicoding.mymovies.core.data.source.local.entity.DetailFilmEntity
import com.dicoding.mymovies.core.data.source.local.entity.DetailSeriesEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyListViewModelTest {

    private lateinit var viewModel: MyListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var filmObserver: Observer<PagedList<DetailFilmEntity>>

    @Mock
    private lateinit var seriesObserver: Observer<PagedList<DetailSeriesEntity>>

    @Mock
    private lateinit var filmPagedList : PagedList<DetailFilmEntity>

    @Mock
    private lateinit var seriesPagedList : PagedList<DetailSeriesEntity>

    @Before
    fun setUp() {
        viewModel = MyListViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteFilm() {
        val dataFavoriteFilm = filmPagedList
        `when`(dataFavoriteFilm.size).thenReturn(2)
        val favorite = MutableLiveData<PagedList<DetailFilmEntity>>()
        favorite.value = dataFavoriteFilm

        `when`(moviesRepository.getFavoriteFilm()).thenReturn(favorite)
        val favoriteEntity = viewModel.getFavoriteFilm().value
        verify(moviesRepository).getFavoriteFilm()
        assertNotNull(favoriteEntity)
        assertEquals(2, favoriteEntity?.size)

        viewModel.getFavoriteFilm().observeForever(filmObserver)
        verify(filmObserver).onChanged(dataFavoriteFilm)
    }

    @Test
    fun getFavoriteSeries() {
        val dataFavoriteSeries = seriesPagedList
        `when`(dataFavoriteSeries.size).thenReturn(2)
        val favorite = MutableLiveData<PagedList<DetailSeriesEntity>>()
        favorite.value = dataFavoriteSeries

        `when`(moviesRepository.getFavoriteSeries()).thenReturn(favorite)
        val favoriteEntity = viewModel.getFavoriteSeries().value
        verify(moviesRepository).getFavoriteSeries()
        assertNotNull(favoriteEntity)
        assertEquals(2, favoriteEntity?.size)

        viewModel.getFavoriteSeries().observeForever(seriesObserver)
        verify(seriesObserver).onChanged(dataFavoriteSeries)
    }
}