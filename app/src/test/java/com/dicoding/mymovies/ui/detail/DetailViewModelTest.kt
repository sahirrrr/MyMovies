package com.dicoding.mymovies.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.mymovies.core.data.MoviesRepository
import com.dicoding.mymovies.core.data.source.local.entity.DetailFilmEntity
import com.dicoding.mymovies.core.data.source.local.entity.DetailSeriesEntity
import com.dicoding.mymovies.core.utils.DataMovies
import com.dicoding.mymovies.vo.Resource
import com.nhaarman.mockitokotlin2.verify
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

    private val dummyPopularFilm = DataMovies.generateDummyPopularFilm()[0]
    private val popularFilmId = dummyPopularFilm.id

    private val dummyPopularSeries = DataMovies.generateDummyPopularSeries()[0]
    private val popularSeriesId = dummyPopularSeries.id


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var filmObserver: Observer<Resource<DetailFilmEntity>>
    @Mock
    private lateinit var seriesObserver: Observer<Resource<DetailSeriesEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(moviesRepository)
        viewModel.setSelectedFilm(popularFilmId)
        viewModel.setSelectedSeries(popularSeriesId)
    }

    @Test
    fun isFavoriteFilm() {
        val dataFavoriteFilm = Resource.success(DataMovies.generateDummyDetailFilm())
        val dummyFavoriteFilm = MutableLiveData<Resource<DetailFilmEntity>>()
        dummyFavoriteFilm.value = dataFavoriteFilm

        `when`(moviesRepository.getDetailFilm(popularFilmId)).thenReturn(dummyFavoriteFilm)
        viewModel.detailFilm = moviesRepository.getDetailFilm(popularFilmId)

        viewModel.setFavoriteFilm()
        verify(moviesRepository).insertFavoriteFilm(dummyFavoriteFilm.value?.data as DetailFilmEntity, true)

        viewModel.detailFilm.observeForever(filmObserver)
        verify(filmObserver).onChanged(dataFavoriteFilm)
    }

    @Test
    fun isFavoriteSeries() {
        val dataFavoriteSeries = Resource.success(DataMovies.generateDummyDetailSeries())
        val dummyFavoriteSeries = MutableLiveData<Resource<DetailSeriesEntity>>()
        dummyFavoriteSeries.value = dataFavoriteSeries

        `when`(moviesRepository.getDetailSeries(popularSeriesId)).thenReturn(dummyFavoriteSeries)
        viewModel.detailSeries = moviesRepository.getDetailSeries(popularSeriesId)

        viewModel.setFavoriteSeries()
        verify(moviesRepository).insertFavoriteSeries(dummyFavoriteSeries.value?.data as DetailSeriesEntity, true)

        viewModel.detailSeries.observeForever(seriesObserver)
        verify(seriesObserver).onChanged(dataFavoriteSeries)
    }
}