package com.dicoding.mymovies.ui.series.adapter

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
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.databinding.ItemRowSeriesBinding
import com.dicoding.mymovies.ui.detail.DetailSeriesActivity

class PopularSeriesAdapter: PagedListAdapter<PopularSeriesEntity ,PopularSeriesAdapter.PopularSeriesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PopularSeriesEntity>() {
            override fun areItemsTheSame(oldItem: PopularSeriesEntity, newItem: PopularSeriesEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: PopularSeriesEntity, newItem: PopularSeriesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularSeriesViewHolder {
        val binding = ItemRowSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularSeriesViewHolder, position: Int) {
        val series = getItem(position)
        if (series != null) {
            holder.bind(series)
        }
    }

    @SuppressLint("SetTextI18n")
    inner class PopularSeriesViewHolder(private val binding: ItemRowSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: PopularSeriesEntity) {
            with(binding) {
                tvItemTitleSeries.text = series.name
                tvItemFirstReleaseSeries.text = series.firstAirDate
                tvItemVoteFilm.text = "${(series.voteAverage * 10).toInt()}%"
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailSeriesActivity::class.java)
                    intent.putExtra(DetailSeriesActivity.EXTRA_SERIES, series.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_ADDRESS + series.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPosterSeries)
            }
        }
    }
}