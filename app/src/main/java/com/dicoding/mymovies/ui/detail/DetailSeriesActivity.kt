package com.dicoding.mymovies.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.BuildConfig
import com.dicoding.mymovies.R
import com.dicoding.mymovies.core.domain.model.DetailSeriesModel
import com.dicoding.mymovies.databinding.ActivityDetailSeriesBinding
import com.dicoding.mymovies.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class DetailSeriesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var binding: ActivityDetailSeriesBinding
    private val viewModel: DetailViewModel by viewModel()
    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getInt(EXTRA_SERIES)
            viewModel.getDetailSeries(tvId).observe(this, { series ->
                if (series.data != null) {
                    when (series.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            populateDetailFilm(series.data)
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Error network issue", Toast.LENGTH_SHORT).show()
                        }
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    }
                }
            })
        }

        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun setFavorite(state: Boolean?) {
        if (state == true) {
            binding.iconBookmark.setImageResource(R.drawable.ic_bookmark_filled)
        } else {
            binding.iconBookmark.setImageResource(R.drawable.ic_bookmark)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailFilm(series: DetailSeriesModel) {
        binding.tvItemTitleSeries.text = series.name
        binding.tvItemLanguageSeries.text = "(${series.originalLanguage.toUpperCase(Locale.getDefault())})"
        binding.tvItemReleaseDateSeries.text = series.firstAirDate
        binding.tvItemOverviewSeries.text = series.overview
        binding.tvItemSeasonSeries.text = "S${series.numberOfSeasons}"
        binding.tvItemEpisodeSeries.text = "E${series.numberOfEpisodes}"
        binding.tvItemPopularitySeries.text = "${(series.voteAverage * 10).toInt()}%"

        Glide.with(this)
                .load(BuildConfig.IMAGE_ADDRESS + series.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(binding.imgPosterSeries)

        Glide.with(this)
                .load(BuildConfig.IMAGE_ADDRESS + series.backdropPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(binding.imgBackDropSeries)

        state = series.favorite
        setFavorite(state)
        binding.iconBookmark.setOnClickListener {
            state = !state
            viewModel.setFavoriteSeries(series, state)
            setFavorite(state)
        }
    }
}