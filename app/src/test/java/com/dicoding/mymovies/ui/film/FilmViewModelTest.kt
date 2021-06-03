package com.dicoding.mymovies.ui.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
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
class FilmViewModelTest {

    private lateinit var viewModel: FilmViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var popularFilmObserver: Observer<Resource<PagedList<PopularFilmEntity>>>

    @Mock
    private lateinit var upcomingFilmObserver: Observer<Resource<PagedList<UpcomingFilmEntity>>>

    @Mock
    private lateinit var popularPagedList : PagedList<PopularFilmEntity>

    @Mock
    private lateinit var upcomingPagedList : PagedList<UpcomingFilmEntity>

    @Before
    fun setUp() {
        viewModel = FilmViewModel(moviesRepository)
    }

    @Test
    fun getPopularFilm() {
        val dataPopularFilm = Resource.success(popularPagedList)
        `when`(dataPopularFilm.data?.size).thenReturn(20)
        val film = MutableLiveData<Resource<PagedList<PopularFilmEntity>>>()
        film.value = dataPopularFilm

        `when`(moviesRepository.getPopularFilm()).thenReturn(film)
        val popularFilmEntity = viewModel.getPopularFilm().value?.data
        verify(moviesRepository).getPopularFilm()
        assertNotNull(popularFilmEntity)
        assertEquals(20, popularFilmEntity?.size)

        viewModel.getPopularFilm().observeForever(popularFilmObserver)
        verify(popularFilmObserver).onChanged(dataPopularFilm)
    }

    @Test
    fun getUpcomingFilm() {
        val dataUpcomingFilm = Resource.success(upcomingPagedList)
        `when`(dataUpcomingFilm.data?.size).thenReturn(20)
        val film = MutableLiveData<Resource<PagedList<UpcomingFilmEntity>>>()
        film.value = dataUpcomingFilm

        `when`(moviesRepository.getUpcomingFilm()).thenReturn(film)
        val upcomingFilmEntity = viewModel.getUpcomingFilm().value?.data
        verify(moviesRepository).getUpcomingFilm()
        assertNotNull(upcomingFilmEntity)
        assertEquals(20, upcomingFilmEntity?.size)

        viewModel.getUpcomingFilm().observeForever(upcomingFilmObserver)
        verify(upcomingFilmObserver).onChanged(dataUpcomingFilm)
    }
}