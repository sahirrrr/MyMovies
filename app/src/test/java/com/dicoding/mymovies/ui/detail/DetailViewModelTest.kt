package com.dicoding.mymovies.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.mymovies.data.MoviesRepository
import com.dicoding.mymovies.data.source.remote.response.DetailFilmResponse
import com.dicoding.mymovies.data.source.remote.response.DetailSeriesResponse
import com.dicoding.mymovies.utils.DataMovies
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val popularFilmResponse = DataMovies.generateDummyPopularFilm()
    private val popularFilmId = popularFilmResponse[0].id
    private val detailFilmResponse = DataMovies.generateDummyDetailFilm()

    private val popularSeriesResponse = DataMovies.generateDummyPopularSeries()
    private val popularSeriesId = popularSeriesResponse[0].id
    private val detailSeriesResponse = DataMovies.generateDummyDetailSeries()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var filmObserver: Observer<DetailFilmResponse>

    @Mock
    private lateinit var seriesObserver: Observer<DetailSeriesResponse>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(moviesRepository)
    }

    @Test
    fun getDetailFilm() {
        val dataDetailFilm = MutableLiveData<DetailFilmResponse>()
        dataDetailFilm.value = detailFilmResponse

        `when`(moviesRepository.getDetailFilm(popularFilmId)).thenReturn(dataDetailFilm)
        viewModel.setSelectedFilm(popularFilmId.toString())
        val detailFilmEntities = viewModel.getDetailFilm().value as DetailFilmResponse
        verify(moviesRepository).getDetailFilm(popularFilmId)

        assertNotNull(detailFilmEntities)
        assertEquals(dataDetailFilm.value?.id, detailFilmEntities.id)
        assertEquals(dataDetailFilm.value?.title, detailFilmEntities.title)
        assertEquals(dataDetailFilm.value?.releaseDate, detailFilmEntities.releaseDate)
        assertEquals(dataDetailFilm.value?.tagLine, detailFilmEntities.tagLine)
        assertEquals(dataDetailFilm.value?.originalLanguage, detailFilmEntities.originalLanguage)
        assertEquals(dataDetailFilm.value?.overview, detailFilmEntities.overview)
        assertEquals(dataDetailFilm.value?.voteAverage, detailFilmEntities.voteAverage)
        assertEquals(dataDetailFilm.value?.posterPath, detailFilmEntities.posterPath)
        assertEquals(dataDetailFilm.value?.backdropPath, detailFilmEntities.backdropPath)

        viewModel.getDetailFilm().observeForever(filmObserver)
        verify(filmObserver).onChanged(detailFilmResponse)
    }

    @Test
    fun getDetailSeries() {
        val dataDetailSeries = MutableLiveData<DetailSeriesResponse>()
        dataDetailSeries.value = detailSeriesResponse

        `when`(moviesRepository.getDetailSeries(popularSeriesId)).thenReturn(dataDetailSeries)
        viewModel.setSelectedSeries(popularSeriesId.toString())
        val detailFilmEntities = viewModel.getDetailSeries().value as DetailSeriesResponse
        verify(moviesRepository).getDetailSeries(popularSeriesId)

        assertNotNull(detailFilmEntities)
        assertEquals(dataDetailSeries.value?.id, detailFilmEntities.id)
        assertEquals(dataDetailSeries.value?.name, detailFilmEntities.name)
        assertEquals(dataDetailSeries.value?.firstAirDate, detailFilmEntities.firstAirDate)
        assertEquals(dataDetailSeries.value?.numberOfSeasons, detailFilmEntities.numberOfSeasons)
        assertEquals(dataDetailSeries.value?.numberOfEpisodes, detailFilmEntities.numberOfEpisodes)
        assertEquals(dataDetailSeries.value?.originalLanguage, detailFilmEntities.originalLanguage)
        assertEquals(dataDetailSeries.value?.overview, detailFilmEntities.overview)
        assertEquals(dataDetailSeries.value?.voteAverage, detailFilmEntities.voteAverage)
        assertEquals(dataDetailSeries.value?.posterPath, detailFilmEntities.posterPath)
        assertEquals(dataDetailSeries.value?.backdropPath, detailFilmEntities.backdropPath)

        viewModel.getDetailSeries().observeForever(seriesObserver)
        verify(seriesObserver).onChanged(detailSeriesResponse)
    }

}