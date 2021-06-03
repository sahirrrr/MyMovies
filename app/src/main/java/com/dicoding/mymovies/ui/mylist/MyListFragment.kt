package com.dicoding.mymovies.ui.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentMyListBinding
import com.dicoding.mymovies.ui.mylist.adapter.MyListFilmAdapter
import com.dicoding.mymovies.ui.mylist.adapter.MyListSeriesAdapter
import com.dicoding.mymovies.viewmodel.ViewModelFactory

class MyListFragment : Fragment() {

    private lateinit var binding: FragmentMyListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[MyListViewModel::class.java]

        favoriteFilm(viewModel)
        favoriteSeries(viewModel)

    }

    private fun favoriteFilm(viewModel: MyListViewModel) {
        val myListFilmAdapter = MyListFilmAdapter()
        viewModel.getFavoriteFilm().observe(viewLifecycleOwner, { favoriteFilm ->
            myListFilmAdapter.submitList(favoriteFilm)
            myListFilmAdapter.notifyDataSetChanged()
        })

        with(binding.rvFavoriteFilm) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = myListFilmAdapter
        }
    }

    private fun favoriteSeries(viewModel: MyListViewModel) {
        val myListSeriesAdapter = MyListSeriesAdapter()
        viewModel.getFavoriteSeries().observe(viewLifecycleOwner, { favoriteSeries ->
            myListSeriesAdapter.submitList(favoriteSeries)
            myListSeriesAdapter.notifyDataSetChanged()
        })

        with(binding.rvFavoriteSeries) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = myListSeriesAdapter
        }
    }
}