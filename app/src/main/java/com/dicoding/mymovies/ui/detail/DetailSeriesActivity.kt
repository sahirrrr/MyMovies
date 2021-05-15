package com.dicoding.mymovies.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.remote.response.DetailSeriesResponse
import com.dicoding.mymovies.databinding.ActivityDetailSeriesBinding
import com.dicoding.mymovies.viewmodel.ViewModelFactory
import java.util.*

class DetailSeriesActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var binding: ActivityDetailSeriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getInt(EXTRA_SERIES)
            val imagesUrl = "https://image.tmdb.org/t/p/original/"
            binding.progressBar.visibility = View.VISIBLE
            viewModel.setSelectedSeries(tvId.toString())
            viewModel.getDetailSeries().observe(this, { series ->
                populateDetailFilm(series, imagesUrl)
                binding.progressBar.visibility = View.GONE
            })
        }

        val returnDetail: ImageView = binding.iconBack
        returnDetail.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailFilm(film: DetailSeriesResponse, imagesUrl: String) {
        binding.tvItemTitleSeries.text = film.name
        binding.tvItemLanguageSeries.text = "(${film.originalLanguage.toUpperCase(Locale.getDefault())})"
        binding.tvItemReleaseDateSeries.text = film.firstAirDate
        binding.tvItemOverviewSeries.text = film.overview
        binding.tvItemSeasonSeries.text = "S${film.numberOfSeasons}"
        binding.tvItemEpisodeSeries.text = "E${film.numberOfEpisodes}"
        binding.tvItemPopularitySeries.text = "${(film.voteAverage * 10).toInt()}%"

        Glide.with(this)
                .load(imagesUrl + film.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(binding.imgPosterSeries)

        Glide.with(this)
                .load(imagesUrl + film.backdropPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(binding.imgBackDropSeries)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.icon_back -> finish()
        }
    }
}