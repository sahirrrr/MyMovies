package com.dicoding.mymovies.ui.film.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.BuildConfig
import com.dicoding.mymovies.R
import com.dicoding.mymovies.core.domain.model.PopularFilmModel
import com.dicoding.mymovies.databinding.ItemRowPopularFilmBinding
import com.dicoding.mymovies.ui.detail.DetailFilmActivity

class PopularFilmAdapter: PagedListAdapter<PopularFilmModel, PopularFilmAdapter.PopularFilmViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PopularFilmModel>() {
            override fun areItemsTheSame(oldItem: PopularFilmModel, newItem: PopularFilmModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: PopularFilmModel, newItem: PopularFilmModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularFilmViewHolder {
        val binding = ItemRowPopularFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularFilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularFilmViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    @SuppressLint("SetTextI18n")
    inner class PopularFilmViewHolder(private val binding: ItemRowPopularFilmBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(popularFilm: PopularFilmModel) {
            with(binding) {
                tvItemTitleFilm.text = popularFilm.title
                tvItemReleaseDateFilm.text = popularFilm.releaseDate
                tvItemVoteFilm.text = "${(popularFilm.voteAverage * 10).toInt()}%"
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM, popularFilm.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(BuildConfig.IMAGE_ADDRESS + popularFilm.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPosterFilm)
            }
        }
    }
}