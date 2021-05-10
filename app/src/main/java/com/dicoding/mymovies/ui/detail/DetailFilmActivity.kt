package com.dicoding.mymovies.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.remote.response.DetailFilmResponse
import com.dicoding.mymovies.databinding.ActivityDetailFilmBinding
import com.dicoding.mymovies.viewmodel.ViewModelFactory
import java.util.*

class DetailFilmActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private lateinit var binding: ActivityDetailFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val moviesId = extras.getInt(EXTRA_FILM)
            val imagesUrl = "https://image.tmdb.org/t/p/original/"
            binding.progressBar.visibility = View.VISIBLE
            viewModel.setSelectedFilm(moviesId.toString())
            viewModel.getDetailFilm().observe(this, { film ->
                populateDetailFilm(film, imagesUrl)
                binding.progressBar.visibility = View.GONE
            })
        }

        val returnDetail: ImageView = binding.iconBack
        returnDetail.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailFilm(film: DetailFilmResponse, imagesUrl: String) {
        binding.tvItemTitleFilm.text = film.title
        binding.tvItemTagline.text = film.tagLine
        binding.tvItemLanguageFilm.text = "(${film.originalLanguage.toUpperCase(Locale.getDefault())})"
        binding.tvItemReleaseDateFilm.text = film.releaseDate
        binding.tvItemOverviewFilm.text = film.overview
        binding.tvItemPopularityFilm.text = "${(film.voteAverage * 10).toInt()}%"

        Glide.with(this)
                .load(imagesUrl + film.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(binding.imgPosterFilm)

        Glide.with(this)
                .load(imagesUrl + film.backdropPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(binding.imgBackDropFilm)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.icon_back -> finish()
        }
    }
}