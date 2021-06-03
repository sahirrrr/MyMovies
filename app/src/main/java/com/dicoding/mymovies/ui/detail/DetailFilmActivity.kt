package com.dicoding.mymovies.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.BuildConfig
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.DetailFilmEntity
import com.dicoding.mymovies.databinding.ActivityDetailFilmBinding
import com.dicoding.mymovies.viewmodel.ViewModelFactory
import com.dicoding.mymovies.vo.Status
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

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val moviesId = extras.getInt(EXTRA_FILM)
            viewModel.setSelectedFilm(moviesId)
        }
            viewModel.detailFilm.observe(this, { film ->
                if (film.data != null) {
                    when (film.status) {
                        Status.SUCCESS -> {
                            populateDetailFilm(film.data)
                            binding.progressBar.visibility = View.GONE
                            val state = film.data.favorite
                            setFavorite(state)
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Error network issue", Toast.LENGTH_SHORT).show()
                        }
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    }
                }
            })

        binding.iconBack.setOnClickListener {
            finish()
        }

        binding.iconBookmark.setOnClickListener {
            viewModel.setFavoriteFilm()
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.iconBookmark.setImageResource(R.drawable.ic_bookmark_filled)
        } else {
            binding.iconBookmark.setImageResource(R.drawable.ic_bookmark)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailFilm(film: DetailFilmEntity) {
        binding.tvItemTitleFilm.text = film.title
        binding.tvItemTagline.text = film.tagLine
        binding.tvItemLanguageFilm.text = "(${film.originalLanguage.toUpperCase(Locale.getDefault())})"
        binding.tvItemReleaseDateFilm.text = film.releaseDate
        binding.tvItemOverviewFilm.text = film.overview
        binding.tvItemPopularityFilm.text = "${(film.voteAverage * 10).toInt()}%"

        Glide.with(this)
                .load(BuildConfig.IMAGE_ADDRESS + film.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(binding.imgPosterFilm)

        Glide.with(this)
                .load(BuildConfig.IMAGE_ADDRESS + film.backdropPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(binding.imgBackDropFilm)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.icon_back -> finish()
        }
    }
}