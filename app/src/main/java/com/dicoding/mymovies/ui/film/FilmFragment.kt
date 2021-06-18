package com.dicoding.mymovies.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentFilmBinding
import com.dicoding.mymovies.ui.film.adapter.PopularFilmAdapter
import com.dicoding.mymovies.ui.film.adapter.UpcomingFilmAdapter
import com.dicoding.mymovies.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding
    private val viewModel: FilmViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFilmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            upcomingMovies(viewModel)
            popularMovies(viewModel)
        }
    }

    private fun upcomingMovies(viewModel: FilmViewModel) {
        val upcomingFilmAdapter = UpcomingFilmAdapter()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getUpcomingFilm().observe(viewLifecycleOwner, { upcomingFilm ->
            when (upcomingFilm.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    upcomingFilmAdapter.submitList(upcomingFilm.data)
                    upcomingFilmAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error network issue", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        })

        with(binding.rvUpcomingFilm) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = upcomingFilmAdapter
        }
    }

    private fun popularMovies(viewModel: FilmViewModel) {
        val filmAdapter = PopularFilmAdapter()

        viewModel.getPopularFilm().observe(viewLifecycleOwner, { popularFilm ->
            when (popularFilm.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    filmAdapter.submitList(popularFilm.data)
                    filmAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error network issue", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        })

        with(binding.rvFilm) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = filmAdapter
        }
    }
}