package com.byansanur.awesomeapp.ui.details

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry.getArguments
import com.byansanur.awesomeapp.R
import com.byansanur.awesomeapp.data.FakeDataPhotos
import com.byansanur.awesomeapp.data.model.PhotoList
import com.byansanur.awesomeapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by byansanur on 9/15/2021.
 * ratbyansanur81@gmail.com
 */
@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class FragmentDetailsPhotosTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val photoList = FakeDataPhotos.generateFakeDataPhotos()[0]

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragmentDetail() {
        val model = photoList
        val args = Bundle().apply {
            getParcelable<PhotoList>("photoList")
        }



        launchFragmentInHiltContainer<FragmentDetailsPhotos> {
            arguments = args
        }

        sleep(3000)

        onView(withId(R.id.toolbarDetails)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbarDetails)).check(matches(withText(model.photographer)))

        onView(withId(R.id.img_details)).check(matches(isDisplayed()))
        onView(withId(R.id.img_details)).check(matches(withContentDescription("image details photographer")))
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}