package com.dicoding.mymovies.ui.series

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class SeriesViewModelTest {

    private lateinit var viewModel: SeriesViewModel

    @Before
    fun setUp() {
        viewModel = SeriesViewModel()
    }

    // Menguji apakah data series tidak null dan memiliki jumlah yg sesuai
    @Test
    fun getFilm() {
        val seriesEntity = viewModel.getSeries()
        assertNotNull(seriesEntity)
        assertEquals(10, seriesEntity.size)
    }
}