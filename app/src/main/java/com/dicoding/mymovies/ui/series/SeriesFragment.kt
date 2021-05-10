package com.dicoding.mymovies.ui.series

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentSeriesBinding
import com.dicoding.mymovies.ui.detail.DetailSeriesActivity
import com.dicoding.mymovies.ui.series.adapter.PopularSeriesAdapter
import com.dicoding.mymovies.ui.series.adapter.TopRatedSeriesAdapter
import com.dicoding.mymovies.viewmodel.ViewModelFactory

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[SeriesViewModel::class.java]

            topRatedSeries(viewModel)
            popularSeries(viewModel)
        }
    }

    private fun topRatedSeries(viewModel: SeriesViewModel) {
        val topRatedSeriesAdapter = TopRatedSeriesAdapter()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getTopRatedSeries().observe(viewLifecycleOwner, { topRatedSeries ->
            binding.progressBar.visibility = View.GONE
            topRatedSeriesAdapter.setSeries(topRatedSeries)
            topRatedSeriesAdapter.notifyDataSetChanged()
        })

        with(binding.rvTopRatedSeries) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = topRatedSeriesAdapter
        }

        topRatedSeriesAdapter.onClickItem = {
            val intent = Intent(activity, DetailSeriesActivity::class.java)
            intent.putExtra(DetailSeriesActivity.EXTRA_SERIES, it.id)
            startActivity(intent)
        }
    }

    private fun popularSeries(viewModel: SeriesViewModel) {
        val popularSeriesAdapter = PopularSeriesAdapter()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getPopularSeries().observe(viewLifecycleOwner, { popularSeries ->
            binding.progressBar.visibility = View.GONE
            popularSeriesAdapter.setSeries(popularSeries)
            popularSeriesAdapter.notifyDataSetChanged()
        })

        with(binding.rvPopularSeries) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularSeriesAdapter
        }

        popularSeriesAdapter.onClickItem = {
            val intent = Intent(activity, DetailSeriesActivity::class.java)
            intent.putExtra(DetailSeriesActivity.EXTRA_SERIES, it.id)
            startActivity(intent)
        }
    }
}