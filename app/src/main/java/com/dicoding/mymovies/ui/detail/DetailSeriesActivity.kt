package com.dicoding.mymovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.SeriesEntity
import com.dicoding.mymovies.databinding.ActivityDetailSeriesBinding

class DetailSeriesActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var binding: ActivityDetailSeriesBinding
    private var seriesEntity: SeriesEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        seriesEntity = intent.getParcelableExtra(EXTRA_SERIES)
        if (seriesEntity != null) {
            with(binding) {
                tvItemTitleSeries.text = seriesEntity?.title
                tvItemDescriptionSeries.text = seriesEntity?.description
                tvItemGenreSeries.text = seriesEntity?.genre
                ratingBarSeries.rating = seriesEntity?.rating!!
                tvItemRatingSeries.text = seriesEntity?.rating.toString()
                tvItemEpisodeNumberSeries.text = seriesEntity?.episode
                Glide.with(applicationContext)
                        .load(seriesEntity?.imagePath)
                        .apply(RequestOptions())
                        .into(imgPosterSeries)
            }
        }
        val returnDetail: ImageView = binding.iconBack
        returnDetail.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.icon_back -> finish()
        }
    }
}