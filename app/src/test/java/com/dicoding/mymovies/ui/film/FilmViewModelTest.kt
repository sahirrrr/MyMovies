package com.dicoding.mymovies.ui.film

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class FilmViewModelTest {

    private lateinit var viewModel: FilmViewModel

    @Before
    fun setUp() {
        viewModel = FilmViewModel()
    }

    // Menguji apakah data film tidak null dan memiliki jumlah yg sesuai
    @Test
    fun getFilm() {
        val filmEntity = viewModel.getFilm()
        assertNotNull(filmEntity)
        assertEquals(10, filmEntity.size)
    }
}