package com.abrahamlay.footballschedule


import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.view.View
import org.junit.Rule
import org.junit.runner.RunWith
import com.abrahamlay.footballschedule.R.id.*
import com.abrahamlay.footballschedule.ui.detail.DetailActivity
import com.abrahamlay.footballschedule.ui.main.MainActivity
import com.abrahamlay.footballschedule.util.MyIdlingResource
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.net.ssl.ExtendedSSLSession
import kotlin.concurrent.thread

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)
    @JvmField var detailActivityRule = ActivityTestRule(DetailActivity::class.java)

    private lateinit var idlingResource: MyIdlingResource

    @Before
    fun init(){
//        idlingResource = activityRule.activity.getIdlingResource()

        idlingResource= MyIdlingResource(activityRule.activity.findViewById(rv_list),View.VISIBLE)

        IdlingRegistry.getInstance().register(idlingResource)

//        idlingResource= MyIdlingResource(detailActivityRule.activity.findViewById(iv_home_team),View.VISIBLE)
//
//        IdlingRegistry.getInstance().register(idlingResource)

    }


    @Test
    fun testRecyclerViewBehaviour() {
        Espresso.onView(ViewMatchers.withId(rv_list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rv_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

    }


    @Test
    fun testAppBehaviour() {
        testRecyclerViewBehaviour()

        Espresso.onView(ViewMatchers.withId(action_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(action_favorite)).perform(ViewActions.click())

        Espresso.onView(
                allOf(
                ViewMatchers.withId(android.support.design.R.id.snackbar_text)
                ,ViewMatchers.withText("Added to favorite")
                ))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(action_menu_favorite))
                .perform(ViewActions.click())

        testRecyclerViewBehaviour()

        Espresso.onView(ViewMatchers.withId(action_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(action_favorite)).perform(ViewActions.click())

        Espresso.onView(
                allOf(
                        ViewMatchers.withId(android.support.design.R.id.snackbar_text)
                        ,ViewMatchers.withText("Removed from favorite")
                ))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(action_refresh))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(action_refresh)).perform(ViewActions.click())
    }


    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}