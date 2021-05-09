package com.dicoding.mymovies

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.mymovies.ui.film.FilmFragment
import com.dicoding.mymovies.ui.mylist.MyListFragment
import com.dicoding.mymovies.ui.series.SeriesFragment

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FilmFragment()
            1 -> fragment = SeriesFragment()
            2 -> fragment = MyListFragment()
        }
        return fragment as Fragment
    }
}