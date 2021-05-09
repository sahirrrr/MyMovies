package com.dicoding.mymovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.FilmEntity
import com.dicoding.mymovies.databinding.ActivityDetailFilmBinding

class DetailFilmActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private lateinit var binding: ActivityDetailFilmBinding
    private var filmEntity: FilmEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        filmEntity = intent.getParcelableExtra(EXTRA_FILM)
        if (filmEntity != null) {
            with(binding) {
                tvItemTitleFilm.text = filmEntity?.title
                tvItemDescriptionFilm.text = filmEntity?.description
                tvItemGenreFilm.text = filmEntity?.genre
                tvItemDurationFilm.text = filmEntity?.duration
                ratingBarFilm.rating = filmEntity?.rating!!
                tvItemRatingFilm.text = filmEntity?.rating.toString()
                Glide.with(applicationContext)
                        .load(filmEntity?.imagePath)
                        .apply(RequestOptions())
                        .into(imgPosterFilm)
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