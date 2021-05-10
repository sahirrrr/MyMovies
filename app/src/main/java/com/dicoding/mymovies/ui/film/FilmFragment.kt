package com.dicoding.mymovies.ui.film

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentFilmBinding
import com.dicoding.mymovies.ui.detail.DetailFilmActivity
import com.dicoding.mymovies.ui.film.adapter.PopularFilmAdapter
import com.dicoding.mymovies.ui.film.adapter.UpcomingFilmAdapter
import com.dicoding.mymovies.viewmodel.ViewModelFactory


class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFilmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FilmViewModel::class.java]
            upcomingMovies(viewModel)
            popularMovies(viewModel)
        }
    }

    private fun upcomingMovies(viewModel: FilmViewModel) {
        val upcomingFilmAdapter = UpcomingFilmAdapter()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getUpcomingFilm().observe(viewLifecycleOwner, { upcomingFilm ->
            binding.progressBar.visibility = View.GONE
            upcomingFilmAdapter.setMovies(upcomingFilm)
            upcomingFilmAdapter.notifyDataSetChanged()
        })

        with(binding.rvUpcomingFilm) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = upcomingFilmAdapter
        }

        upcomingFilmAdapter.onClickItem = {
            val intent = Intent(activity, DetailFilmActivity::class.java)
            intent.putExtra(DetailFilmActivity.EXTRA_FILM, it.id)
            startActivity(intent)
        }
    }

    private fun popularMovies(viewModel: FilmViewModel) {
        val filmAdapter = PopularFilmAdapter()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getPopularFilm().observe(viewLifecycleOwner, { film ->
            binding.progressBar.visibility = View.GONE
            filmAdapter.setMovies(film)
            filmAdapter.notifyDataSetChanged()
        })

        with(binding.rvFilm) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = filmAdapter
        }

        filmAdapter.onClickItem = {
            val intent = Intent(activity, DetailFilmActivity::class.java)
            intent.putExtra(DetailFilmActivity.EXTRA_FILM, it.id)
            startActivity(intent)
        }
    }
}