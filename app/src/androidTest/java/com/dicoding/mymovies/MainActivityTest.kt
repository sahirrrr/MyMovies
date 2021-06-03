package com.dicoding.mymovies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.mymovies.utils.DataMovies
import com.dicoding.mymovies.utils.EspressoIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dataPopularFilm = DataMovies.generateDummyPopularFilm()
    private val dataUpcomingFilm = DataMovies.generateDummyUpcomingFilm()

    private val dataPopularSeries = DataMovies.generateDummyPopularSeries()
    private val dataTopRatedSeries = DataMovies.generateDummyTopRatedSeries()

    private val dataDetailFilm = DataMovies.generateDummyDetailFilm()
    private val dataDetailSeries = DataMovies.generateDummyDetailSeries()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource)
    }

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource)
    }

    // Testing recyclerview rv_film yang ada pada FilmFragment
    @Test
    fun loadPopularFilm() {
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataPopularFilm.size))
    }


    // Testing recyclerview rv_upcoming_film yang ada pada FilmFragment
    @Test
    fun loadUpcomingFilm() {
        onView(withId(R.id.rv_upcoming_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_upcoming_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataUpcomingFilm.size))
    }


    // Testing Detail Film Activity apakah data yg ditampilkan sudah benar
    @Test
    fun loadPopularDetailFilm() {
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_item_title_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title_film)).check(matches(withText(dataDetailFilm.title)))
        onView(withId(R.id.tv_item_language_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_language_film)).check(matches(withText("(${dataDetailFilm.originalLanguage.toUpperCase()})")))
        onView(withId(R.id.tv_item_release_date_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release_date_film)).check(matches(withText(dataDetailFilm.releaseDate)))
        onView(withId(R.id.tv_item_tagline)).check(matches(withText(dataDetailFilm.tagLine)))
        onView(withId(R.id.tv_item_overview_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_overview_film)).check(matches(withText(dataDetailFilm.overview)))
        onView(withId(R.id.tv_item_popularity_film)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_popularity_film)).check(matches(withText("${(dataDetailFilm.voteAverage * 10).toInt()}%")))
    }

    // Testing recyclerview rv_popular_series yang ada pada SeriesFragment
    @Test
    fun loadPopularSeries() {
        onView(withText("Series")).perform(ViewActions.click())
        onView(withId(R.id.rv_popular_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataPopularSeries.size))
    }

    // Testing recyclerview rv_top_rated_series yang ada pada SeriesFragment
    @Test
    fun loadTopRatedSeries() {
        onView(withText("Series")).perform(ViewActions.click())
        onView(withId(R.id.rv_top_rated_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_top_rated_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataTopRatedSeries.size))
    }

    // Testing Detail Series Activity apakah data yg ditampilkan sudah benar
    @Test
    fun loadDetailSeries() {
        onView(withText("Series")).perform(ViewActions.click())
        onView(withId(R.id.rv_popular_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_item_title_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title_series)).check(matches(withText(dataDetailSeries.name)))
        onView(withId(R.id.tv_item_season_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_season_series)).check(matches(withText("S${dataDetailSeries.numberOfSeasons}")))
        onView(withId(R.id.tv_item_episode_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_episode_series)).check(matches(withText("E${dataDetailSeries.numberOfEpisodes}")))
        onView(withId(R.id.tv_item_release_date_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release_date_series)).check(matches(withText(dataDetailSeries.firstAirDate)))
        onView(withId(R.id.tv_item_language_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_language_series)).check(matches(withText("(${dataDetailSeries.originalLanguage.toUpperCase()})")))
        onView(withId(R.id.tv_item_overview_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_overview_series)).check(matches(withText(dataDetailSeries.overview)))
        onView(withId(R.id.tv_item_popularity_series)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_popularity_series)).check(matches(withText("${(dataDetailSeries.voteAverage * 10).toInt()}%")))
    }

    @Test
    fun loadMyListFilm() {
        onView(withText("My List")).perform(ViewActions.click())
        onView(withId(R.id.rv_favorite_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataPopularFilm.size))
    }


    @Test
    fun loadMyListSeries() {
        onView(withText("My List")).perform(ViewActions.click())
        onView(withId(R.id.rv_favorite_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataPopularSeries.size))
    }

    @Test
    fun addFilmToMyList() {
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.icon_bookmark)).perform(ViewActions.click())
    }

    @Test
    fun addSeriesToMyList() {
        onView(withText("Series")).perform(ViewActions.click())
        onView(withId(R.id.rv_popular_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.icon_bookmark)).perform(ViewActions.click())
    }

}