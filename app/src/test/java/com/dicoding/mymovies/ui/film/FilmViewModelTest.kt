package com.dicoding.mymovies.ui.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.utils.DataMovies
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
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
    private lateinit var popularFilmObserver: Observer<List<PopularFilmEntity>>

    @Mock
    private lateinit var upcomingFilmObserver: Observer<List<UpcomingFilmEntity>>

    @Before
    fun setUp() {
        viewModel = FilmViewModel(moviesRepository)
    }

    @Test
    fun getPopularFilm() {
        val dataPopularFilm = DataMovies.generateDummyPopularFilm()
        val film = MutableLiveData<List<PopularFilmEntity>>()
        film.value = dataPopularFilm

        `when`(moviesRepository.getPopularFilm()).thenReturn(film)
        val popularFilmEntity = viewModel.getPopularFilm().value
        verify(moviesRepository).getPopularFilm()
        assertNotNull(popularFilmEntity)
        assertEquals(20, popularFilmEntity?.size)

        viewModel.getPopularFilm().observeForever(popularFilmObserver)
        verify(popularFilmObserver).onChanged(dataPopularFilm)
    }

    @Test
    fun getUpcomingFilm() {
        val dataUpcomingFilm = DataMovies.generateDummyUpcomingFilm()
        val film = MutableLiveData<List<UpcomingFilmEntity>>()
        film.value = dataUpcomingFilm

        `when`(moviesRepository.getUpcomingFilm()).thenReturn(film)
        val upcomingFilmEntity = viewModel.getUpcomingFilm().value
        verify(moviesRepository).getUpcomingFilm()
        assertNotNull(upcomingFilmEntity)
        assertEquals(20, upcomingFilmEntity?.size)

        viewModel.getUpcomingFilm().observeForever(upcomingFilmObserver)
        verify(upcomingFilmObserver).onChanged(dataUpcomingFilm)
    }
}