package com.byansanur.awesomeapp.ui.home

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.byansanur.awesomeapp.R
import com.byansanur.awesomeapp.data.FakeDataPhotos
import com.byansanur.awesomeapp.launchFragmentInHiltContainer
import com.byansanur.awesomeapp.ui.ActivityMain
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by byansanur on 9/15/2021.
 * ratbyansanur81@gmail.com
 */
@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class FragmentHomeTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val photoList = FakeDataPhotos.generateFakeDataPhotos()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun mainActivityTest() {
        launchActivity<ActivityMain>()
    }

    @Test
    fun testLoadStartDestination() {
        launchFragmentInHiltContainer<FragmentHome> { }
        sleep(2000)
    }

    @Test
    fun testViewDisplayed() {
        launchFragmentInHiltContainer<FragmentHome> { }
        sleep(3000)
        onView(withId(R.id.rv_list_photo)).check(matches(isDisplayed()))
        onView(withId(R.id.grid_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.linear_menu)).check(matches(isDisplayed()))
    }

    @Test
    fun testClickOptionMenuItem() {
        launchFragmentInHiltContainer<FragmentHome> { }
        sleep(2000)

        onView(withId(R.id.linear_menu)).perform(click())
        onView(withId(R.id.grid_menu)).perform(click())
    }

    @Test
    fun testScrollItem() {
        launchFragmentInHiltContainer<FragmentHome> { }
        sleep(2000)
        onView(withId(R.id.rv_list_photo)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
    }

    @Test
    fun testItemClickListener() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<FragmentHome> {
            Navigation.setViewNavController(requireView(), navController)
        }
        sleep(3000)
        onView(withId(R.id.rv_list_photo)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        val nav = FragmentHomeDirections.actionFragmentHomeToFragmentDetailsPhotos(photoList[0])

        verify(navController).navigate(nav)

    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}