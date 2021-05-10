package com.dicoding.mymovies.ui.series.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mymovies.R
import com.dicoding.mymovies.data.source.local.entity.PopularSeriesEntity
import com.dicoding.mymovies.databinding.ItemRowSeriesBinding

class PopularSeriesAdapter: RecyclerView.Adapter<PopularSeriesAdapter.PopularSeriesViewHolder>() {
    private var imgUrl = "https://image.tmdb.org/t/p/original/"
    private var listSeries = ArrayList<PopularSeriesEntity>()
    var onClickItem: ((PopularSeriesEntity) -> Unit)? = null

    fun setSeries(series: List<PopularSeriesEntity>?) {
        if (series == null) return
        this.listSeries.clear()
        this.listSeries.addAll(series)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularSeriesViewHolder {
        val binding = ItemRowSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularSeriesViewHolder, position: Int) {
        val series = listSeries[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int = listSeries.size

    @SuppressLint("SetTextI18n")
    inner class PopularSeriesViewHolder(private val binding: ItemRowSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: PopularSeriesEntity) {
            with(binding) {
                tvItemTitleSeries.text = series.name
                tvItemFirstReleaseSeries.text = series.firstAirDate
                tvItemVoteFilm.text = "${(series.voteAverage * 10).toInt()}%"

                Glide.with(itemView.context)
                    .load(imgUrl + series.posterPath)
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