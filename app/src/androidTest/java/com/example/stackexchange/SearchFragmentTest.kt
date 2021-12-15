package com.example.stackexchange

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.example.stackexchange.ui.view.fragment.SearchFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @get:Rule
    var fragmentTestRule: FragmentTestRule<*, SearchFragment> =
        FragmentTestRule.create(SearchFragment::class.java)

    @Test
    fun searchEntry() {
        onView(withId(R.id.search_entry))
            .perform(typeText("John"), closeSoftKeyboard())

        onView(withId(R.id.search_button))
            .perform(click())
    }


    @Test
    fun scrollToTenthItem() {
        onView(withId(R.id.search_entry))
            .perform(typeText("Kevin"), closeSoftKeyboard())

        onView(withId(R.id.search_button))
            .perform(click())

        onView(withId(R.id.user_list))
            .perform(
                RecyclerViewActions.
            scrollToPosition<RecyclerView.ViewHolder>(10))
    }
}