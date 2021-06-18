package com.dicoding.mymovies.core.di

import androidx.room.Room
import com.dicoding.mymovies.BuildConfig
import com.dicoding.mymovies.core.data.MoviesRepository
import com.dicoding.mymovies.core.data.source.local.LocalDataSource
import com.dicoding.mymovies.core.data.source.local.room.MyMoviesDatabase
import com.dicoding.mymovies.core.data.source.remote.RemoteDataSource
import com.dicoding.mymovies.core.data.source.remote.network.ApiService
import com.dicoding.mymovies.core.domain.IMoviesRepository
import com.dicoding.mymovies.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MyMoviesDatabase>().MoviesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MyMoviesDatabase::class.java,
            "my_movies.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MAIN_ADDRESS)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IMoviesRepository> { MoviesRepository(get(), get(), get()) }
}