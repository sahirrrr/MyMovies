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
import com.dicoding.mymovies.data.source.local.entity.TopRatedSeriesEntity
import com.dicoding.mymovies.databinding.ItemRowSeriesBinding
import com.dicoding.mymovies.ui.detail.DetailSeriesActivity

class TopRatedSeriesAdapter : PagedListAdapter<TopRatedSeriesEntity ,TopRatedSeriesAdapter.TopRatedSeriesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TopRatedSeriesEntity>() {
            override fun areItemsTheSame(oldItem: TopRatedSeriesEntity, newItem: TopRatedSeriesEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TopRatedSeriesEntity, newItem: TopRatedSeriesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedSeriesViewHolder {
        val binding = ItemRowSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopRatedSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedSeriesViewHolder, position: Int) {
        val series = getItem(position)
        if (series != null) {
            holder.bind(series)
        }
    }

    @SuppressLint("SetTextI18n")
    inner class TopRatedSeriesViewHolder(private val binding: ItemRowSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: TopRatedSeriesEntity) {
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