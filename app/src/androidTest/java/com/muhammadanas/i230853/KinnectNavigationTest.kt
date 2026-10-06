package com.muhammadanas.i230853

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/*
  TESTS · Navigation
  Espresso tests for two key flows: Home → Comments → back, and Log in → Menu → Log out.
 */
@RunWith(AndroidJUnit4::class)
class KinnectNavigationTest {

    // Test 1: open Comments from the Home post, then Back returns to Home
    @Test
    fun homeToCommentsAndBack() {
        ActivityScenario.launch(HomeActivity::class.java).use {
            // Scroll to the post's Comment button and tap it
            onView(withId(R.id.ll_action_comment)).perform(scrollTo(), click())

            // Comments screen is showing
            onView(withId(R.id.iv_comments_back)).check(matches(isDisplayed()))

            // Back goes to Home again
            pressBack()
            onView(withId(R.id.tab_home)).check(matches(isDisplayed()))
        }
    }

    // Test 2 (multistep): Log in → Menu tab → Log out lands on Log in with an empty back stack
    @Test
    fun loginMenuLogoutClearsBackStack() {
        ActivityScenario.launch(LoginActivity::class.java)

        // Log in → Home
        onView(withId(R.id.btn_log_in)).perform(click())

        // Home → Menu tab
        onView(withId(R.id.tab_menu)).perform(click())

        // Scroll down to Log out and tap it → Log in screen again
        onView(withId(R.id.ll_log_out)).perform(scrollTo(), click())
        onView(withId(R.id.btn_log_in)).check(matches(isDisplayed()))

        // Back from Log in must close the app, because Home and Menu were cleared away.
        // If anything were left underneath, it would come back to the front (RESUMED).
        pressBackUnconditionally()
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val resumed = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            assertTrue("Back stack should be empty after Log out", resumed.isEmpty())
        }
    }
}