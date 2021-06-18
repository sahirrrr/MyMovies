package com.dicoding.mymovies.ui.film.adapter

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
import com.dicoding.mymovies.core.domain.model.UpcomingFilmModel
import com.dicoding.mymovies.databinding.ItemRowUpcomingFilmBinding
import com.dicoding.mymovies.ui.detail.DetailFilmActivity

class UpcomingFilmAdapter: PagedListAdapter<UpcomingFilmModel ,UpcomingFilmAdapter.UpcomingFilmViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UpcomingFilmModel>() {
            override fun areItemsTheSame(oldItem: UpcomingFilmModel, newItem: UpcomingFilmModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: UpcomingFilmModel, newItem: UpcomingFilmModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingFilmViewHolder {
        val binding = ItemRowUpcomingFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingFilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingFilmViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    inner class UpcomingFilmViewHolder(private val binding: ItemRowUpcomingFilmBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(upcomingFilm: UpcomingFilmModel) {
            with(binding) {
                tvItemTitleFilm.text = upcomingFilm.title
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM, upcomingFilm.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(BuildConfig.IMAGE_ADDRESS + upcomingFilm.backdropPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPosterFilm)
            }
        }
    }
}