package com.dicoding.mymovies.ui.film.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.UpcomingFilmEntity
import com.dicoding.mymovies.databinding.ItemRowUpcomingFilmBinding

class UpcomingFilmAdapter: RecyclerView.Adapter<UpcomingFilmAdapter.UpcomingFilmViewHolder>() {
    private var imgUrl = "https://image.tmdb.org/t/p/original/"
    private var listMovies = ArrayList<UpcomingFilmEntity>()
    var onClickItem: ((UpcomingFilmEntity) -> Unit)? = null

    fun setMovies(movies: List<UpcomingFilmEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingFilmViewHolder {
        val binding = ItemRowUpcomingFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingFilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingFilmViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class UpcomingFilmViewHolder(private val binding: ItemRowUpcomingFilmBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(film: UpcomingFilmEntity) {
            with(binding) {
                tvItemTitleFilm.text = film.title
                Glide.with(itemView.context)
                        .load(imgUrl + film.backdropPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPosterFilm)
            }
        }

        init {
            itemView.setOnClickListener {
                onClickItem?.invoke(listMovies[absoluteAdapterPosition])
            }
        }
    }
}