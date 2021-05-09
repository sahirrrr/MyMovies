package com.dicoding.mymovies.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.SeriesEntity
import com.dicoding.mymovies.databinding.ItemRowSeriesBinding

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private var listSeries = ArrayList<SeriesEntity>()
    var onClickItem: ((SeriesEntity) -> Unit)? = null

    fun setSeries(series: List<SeriesEntity>?) {
        if (series == null) return
        this.listSeries.clear()
        this.listSeries.addAll(series)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = ItemRowSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = listSeries[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int = listSeries.size

    inner class SeriesViewHolder(private val binding: ItemRowSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesEntity) {
            with(binding) {
                tvItemTitleSeries.text = series.title
                tvItemGenreSeries.text = series.genre
                tvItemEpisodeNumberSeries.text = series.episode
                tvItemRatingSeries.text = series.rating.toString()
                ratingBarSeries.rating = series.rating

                Glide.with(itemView.context)
                    .load(series.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPosterSeries)
            }
        }
        init {
            itemView.setOnClickListener {
                onClickItem?.invoke(listSeries[absoluteAdapterPosition])
            }
        }
    }
}