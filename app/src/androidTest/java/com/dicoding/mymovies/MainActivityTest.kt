package com.dicoding.mymovies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.mymovies.utils.DataMovies
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val getFilm = DataMovies.generateFilm()
    private val getSeries = DataMovies.generateSeries()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Testing recyclerview rv_film yang ada pada FilmFragment
    @Test
    fun loadFilm() {
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(getFilm.size))
    }

    // Testing Detail Film Activity apakah data yg ditampilkan sudah benar
    @Test
    fun loadDetailFilm() {
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_item_title_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title_film)).check(matches(withText(getFilm[0].title)))
        onView(withId(R.id.tv_item_rating_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating_film)).check(matches(withText(getFilm[0].rating.toString())))
        onView(withId(R.id.tv_item_genre_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre_film)).check(matches(withText(getFilm[0].genre)))
        onView(withId(R.id.tv_item_duration_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration_film)).check(matches(withText(getFilm[0].duration)))
        onView(withId(R.id.tv_item_description_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description_film)).check(matches(withText(getFilm[0].description)))
    }

    // Testing recyclerview rv_series yang ada pada SeriesFragment
    @Test
    fun loadSeries() {
        onView(withText("Series")).perform(ViewActions.click())
        onView(withId(R.id.rv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(getSeries.size))
    }

    // Testing Detail Series Activity apakah data yg ditampilkan sudah benar
    @Test
    fun loadDetailSeries() {
        onView(withText("Series")).perform(ViewActions.click())
        onView(withId(R.id.rv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_item_title_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title_series)).check(matches(withText(getSeries[0].title)))
        onView(withId(R.id.tv_item_rating_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating_series)).check(matches(withText(getSeries[0].rating.toString())))
        onView(withId(R.id.tv_item_genre_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre_series)).check(matches(withText(getSeries[0].genre)))
        onView(withId(R.id.tv_item_episode_number_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_episode_number_series)).check(matches(withText(getSeries[0].episode)))
        onView(withId(R.id.tv_item_description_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description_series)).check(matches(withText(getSeries[0].description)))
    }
}