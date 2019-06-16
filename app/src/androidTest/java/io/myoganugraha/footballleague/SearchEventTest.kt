package io.myoganugraha.footballleague

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.myoganugraha.footballleague.view.LeagueDetail.LeagueDetail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Intent
import android.widget.EditText


@RunWith(AndroidJUnit4::class)
class SearchEventTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<LeagueDetail>(LeagueDetail::class.java, true, false)

    @Test
    fun searchEvent() {

        val intent = Intent()
        intent.putExtra("leagueID", "4328")
        intent.putExtra("leagueName", "English Premier League")

        activityRule.launchActivity(intent)

        Thread.sleep(2500)

        Thread.sleep(2000)
        onView(withId(R.id.action_search)).perform(click())

        Thread.sleep(2000)
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Arsenal vs Chelsea")).perform(pressImeActionButton())
        Thread.sleep(4000)
    }

}