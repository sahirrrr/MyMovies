package com.dicoding.mymovies.ui.film

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.data.FilmEntity
import com.dicoding.mymovies.R
import com.dicoding.mymovies.databinding.ItemRowFilmBinding

class FilmAdapter: RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private var listMovies = ArrayList<FilmEntity>()
    var onClickItem: ((FilmEntity) -> Unit)? = null

    fun setMovies(movies: List<FilmEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemRowFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class FilmViewHolder(private val binding: ItemRowFilmBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmEntity) {
            with(binding) {
                tvItemTitleFilm.text = film.title
                tvItemGenreFilm.text = film.genre
                tvItemDurationFilm.text = film.duration
                tvItemRatingFilm.text = film.rating.toString()
                ratingBarFilm.rating = film.rating

                Glide.with(itemView.context)
                        .load(film.imagePath)
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