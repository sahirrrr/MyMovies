package com.dicoding.mymovies.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.dicoding.mymovies.core.data.source.remote.network.ApiService
import com.dicoding.mymovies.core.data.source.remote.response.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class RemoteDataSource(private val apiService: ApiService) {

    fun getPopularFilm(): Flowable<ApiResponse<List<PopularFilmResults>>> {
        val responseResults = PublishSubject.create<ApiResponse<List<PopularFilmResults>>>()
        val client = apiService.getPopularFilm()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val responseResult = response.results
                responseResults.onNext(if (responseResult.isNotEmpty()) ApiResponse.Success(responseResult) else ApiResponse.Empty)
            }, { error ->
                responseResults.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResults.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getUpcomingFilm(): Flowable<ApiResponse<List<UpcomingFilmResults>>> {
        val responseResults = PublishSubject.create<ApiResponse<List<UpcomingFilmResults>>>()
        val client = apiService.getUpcomingFilm()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val responseResult = response.results
                responseResults.onNext(if (responseResult.isNotEmpty()) ApiResponse.Success(responseResult) else ApiResponse.Empty)
            }, { error ->
                responseResults.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResults.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getTopRatedSeries(): Flowable<ApiResponse<List<TopRatedSeriesResults>>> {
        val responseResults = PublishSubject.create<ApiResponse<List<TopRatedSeriesResults>>>()
        val client = apiService.getTopRatedSeries()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val responseResult = response.results
                responseResults.onNext(if (responseResult.isNotEmpty()) ApiResponse.Success(responseResult) else ApiResponse.Empty)
            }, { error ->
                responseResults.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResults.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getPopularSeries(): Flowable<ApiResponse<List<PopularSeriesResults>>> {
        val responseResults = PublishSubject.create<ApiResponse<List<PopularSeriesResults>>>()
        val client = apiService.getPopularSeries()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val responseResult = response.results
                responseResults.onNext(if (responseResult.isNotEmpty()) ApiResponse.Success(responseResult) else ApiResponse.Empty)
            }, { error ->
                responseResults.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResults.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getDetailFilm(filmId: Int): Flowable<ApiResponse<DetailFilmResponse>> {
        val responseResults = PublishSubject.create<ApiResponse<DetailFilmResponse>>()
        val client = apiService.getDetailFilm(filmId)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                responseResults.onNext(ApiResponse.Success(response))
            }, { error ->
                responseResults.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResults.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getDetailSeries(tvId: Int): Flowable<ApiResponse<DetailSeriesResponse>> {
        val responseResults = PublishSubject.create<ApiResponse<DetailSeriesResponse>>()
        val client = apiService.getDetailSeries(tvId)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                responseResults.onNext(ApiResponse.Success(response))
            }, { error ->
                responseResults.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource, cause", error.toString())
            })
        return responseResults.toFlowable(BackpressureStrategy.BUFFER)
    }
}