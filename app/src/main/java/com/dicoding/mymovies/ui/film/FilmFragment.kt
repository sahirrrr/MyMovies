package com.dicoding.mymovies.ui.film

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mymovies.databinding.FragmentFilmBinding
import com.dicoding.mymovies.ui.detail.DetailFilmActivity


class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding
    private val viewModel:FilmViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFilmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val film = viewModel.getFilm()

            val filmAdapter = FilmAdapter()
            filmAdapter.setMovies(film)

            with(binding.rvFilm) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = filmAdapter
            }

            filmAdapter.onClickItem = {
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_FILM, it)
                startActivity(intent)
            }
        }
    }
}