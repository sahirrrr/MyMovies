package com.dicoding.mymovies.di

import com.dicoding.mymovies.core.domain.usecase.MoviesInteractor
import com.dicoding.mymovies.core.domain.usecase.MoviesUseCase
import com.dicoding.mymovies.ui.detail.DetailViewModel
import com.dicoding.mymovies.ui.film.FilmViewModel
import com.dicoding.mymovies.ui.mylist.MyListViewModel
import com.dicoding.mymovies.ui.series.SeriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailViewModel(get()) }
    viewModel { FilmViewModel(get()) }
    viewModel { SeriesViewModel(get()) }
    viewModel { MyListViewModel(get()) }
}