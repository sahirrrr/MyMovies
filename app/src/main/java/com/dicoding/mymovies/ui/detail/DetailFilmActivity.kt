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
import com.dicoding.mymovies.core.domain.model.DetailFilmModel
import com.dicoding.mymovies.databinding.ActivityDetailFilmBinding
import com.dicoding.mymovies.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class DetailFilmActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private lateinit var binding: ActivityDetailFilmBinding
    private val viewModel: DetailViewModel by viewModel()
    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val extras = intent.extras
        if (extras != null) {
            val moviesId = extras.getInt(EXTRA_FILM)
            viewModel.getDetailFilm(moviesId).observe(this, { film ->
                if (film.data != null) {
                    when (film.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            populateDetailFilm(film.data)
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

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.iconBookmark.setImageResource(R.drawable.ic_bookmark_filled)
        } else {
            binding.iconBookmark.setImageResource(R.drawable.ic_bookmark)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateDetailFilm(film: DetailFilmModel) {
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

        state = film.favorite
        setFavorite(state)
        binding.iconBookmark.setOnClickListener {
            state = !state
            viewModel.setFavoriteFilm(film, state)
            setFavorite(state)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.icon_back -> finish()
        }
    }
}