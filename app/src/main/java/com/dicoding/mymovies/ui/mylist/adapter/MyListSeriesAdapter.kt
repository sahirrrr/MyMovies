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
import com.dicoding.mymovies.core.domain.model.DetailSeriesModel
import com.dicoding.mymovies.databinding.ItemRowFavoriteBinding
import com.dicoding.mymovies.ui.detail.DetailSeriesActivity

class MyListSeriesAdapter: PagedListAdapter<DetailSeriesModel, MyListSeriesAdapter.FavoriteSeriesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailSeriesModel>() {
            override fun areItemsTheSame(oldItem: DetailSeriesModel, newItem: DetailSeriesModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: DetailSeriesModel, newItem: DetailSeriesModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteSeriesViewHolder {
        val binding = ItemRowFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteSeriesViewHolder, position: Int) {
        val favorite = getItem(position)
        if (favorite != null) {
            holder.bind(favorite)
        }
    }

    inner class FavoriteSeriesViewHolder(private val binding: ItemRowFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteSeries: DetailSeriesModel) {
            with(binding) {
                tvItemTitleFilm.text = favoriteSeries.name
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailSeriesActivity::class.java)
                    intent.putExtra(DetailSeriesActivity.EXTRA_SERIES, favoriteSeries.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_ADDRESS + favoriteSeries.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPosterFilm)
            }
        }
    }
}