package com.dicoding.mymovies.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentSeriesBinding
import com.dicoding.mymovies.ui.series.adapter.PopularSeriesAdapter
import com.dicoding.mymovies.ui.series.adapter.TopRatedSeriesAdapter
import com.dicoding.mymovies.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private val viewModel: SeriesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            topRatedSeries()
            popularSeries()
        }
    }

    private fun topRatedSeries() {
        val topRatedSeriesAdapter = TopRatedSeriesAdapter()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getTopRatedSeries().observe(viewLifecycleOwner, { topRatedSeries ->
            when (topRatedSeries.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    topRatedSeriesAdapter.submitList(topRatedSeries.data)
                    topRatedSeriesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error network issue", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        })

        with(binding.rvTopRatedSeries) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = topRatedSeriesAdapter
        }
    }

    private fun popularSeries() {
        val popularSeriesAdapter = PopularSeriesAdapter()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getPopularSeries().observe(viewLifecycleOwner, { popularSeries ->
            when (popularSeries.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    popularSeriesAdapter.submitList(popularSeries.data)
                    popularSeriesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error network issue", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        })

        with(binding.rvPopularSeries) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularSeriesAdapter
        }
    }
}