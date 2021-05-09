package com.dicoding.mymovies.ui.film.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.data.source.local.entity.PopularFilmEntity
import com.dicoding.mymovies.R
import com.dicoding.mymovies.databinding.ItemRowFilmBinding

class PopularFilmAdapter: RecyclerView.Adapter<PopularFilmAdapter.PopularFilmViewHolder>() {
    private var imgUrl = "https://image.tmdb.org/t/p/original/"
    private var listMovies = ArrayList<PopularFilmEntity>()
    var onClickItem: ((PopularFilmEntity) -> Unit)? = null

    fun setMovies(movies: List<PopularFilmEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularFilmViewHolder {
        val binding = ItemRowFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularFilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularFilmViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    @SuppressLint("SetTextI18n")
    inner class PopularFilmViewHolder(private val binding: ItemRowFilmBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(popularFilm: PopularFilmEntity) {
            with(binding) {
                tvItemTitleFilm.text = popularFilm.title
                tvItemReleaseDateFilm.text = popularFilm.releaseDate
                tvItemVoteFilm.text = "${(popularFilm.voteAverage * 10).toInt()}%"

                Glide.with(itemView.context)
                        .load(imgUrl + popularFilm.posterPath)
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