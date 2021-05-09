package com.dicoding.mymovies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.databinding.ActivityDetailFilmBinding

class DetailFilmActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private lateinit var binding: ActivityDetailFilmBinding
    private var popularFilmEntity: PopularFilmEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        popularFilmEntity = intent.getParcelableExtra(EXTRA_FILM)
        if (popularFilmEntity != null) {
            with(binding) {
                tvItemTitleFilm.text = popularFilmEntity?.title
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