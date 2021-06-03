package com.dicoding.mymovies.ui.mylist.adapter

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
import com.dicoding.mymovies.data.source.local.entity.DetailFilmEntity
import com.dicoding.mymovies.databinding.ItemRowFavoriteBinding
import com.dicoding.mymovies.ui.detail.DetailFilmActivity

class MyListFilmAdapter: PagedListAdapter<DetailFilmEntity, MyListFilmAdapter.FavoriteFilmViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailFilmEntity>() {
            override fun areItemsTheSame(oldItem: DetailFilmEntity, newItem: DetailFilmEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: DetailFilmEntity, newItem: DetailFilmEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteFilmViewHolder {
        val binding = ItemRowFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteFilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteFilmViewHolder, position: Int) {
        val favorite = getItem(position)
        if (favorite != null) {
            holder.bind(favorite)
        }
    }

    inner class FavoriteFilmViewHolder(private val binding: ItemRowFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteFilm: DetailFilmEntity) {
            with(binding) {
                tvItemTitleFilm.text = favoriteFilm.title
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM, favoriteFilm.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_ADDRESS + favoriteFilm.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPosterFilm)
            }
        }
    }
}