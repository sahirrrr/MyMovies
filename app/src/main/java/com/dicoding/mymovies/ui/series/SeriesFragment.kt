package com.dicoding.mymovies.ui.series

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentSeriesBinding
import com.dicoding.mymovies.ui.detail.DetailSeriesActivity

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private val viewModel: SeriesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
//            val series = viewModel.getSeries()
//
//            val seriesAdapter = SeriesAdapter()
//            seriesAdapter.setSeries(series)
//
//            with(binding.rvSeries) {
//                layoutManager = LinearLayoutManager(context)
//                setHasFixedSize(true)
//                adapter = seriesAdapter
//            }
//
//            seriesAdapter.onClickItem = {
//                val intent = Intent(activity, DetailSeriesActivity::class.java)
//                intent.putExtra(DetailSeriesActivity.EXTRA_SERIES, it)
//                startActivity(intent)
//            }
        }
    }
}