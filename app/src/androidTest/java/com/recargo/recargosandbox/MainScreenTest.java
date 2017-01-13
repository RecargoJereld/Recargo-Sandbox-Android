package com.recargo.recargosandbox;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.recargo.recargosandbox.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by jereld on 1/6/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainScreenTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnListNavigationItem_ShowListScreen() {
        // Click on List nav bar item
        onView(withId(R.id.list)).perform(click());

        // Verify that fragment container contains a view with id R.id.recycler_view
        onView(withId(R.id.fragment_container))
                .check(selectedDescendantsMatch(
                        isAssignableFrom(RecyclerView.class),
                        withId(R.id.recycler_view)));
    }

    @Test
    public void clickOnMapNavigationItem_ShowMapScreen() {
        // Click on List nav bar item
        onView(withId(R.id.map)).perform(click());

        // Verify that fragment container displays a view with id R.id.map_container
        onView(withId(R.id.fragment_container))
                .check(selectedDescendantsMatch(
                        withId(R.id.map_container),
                        isDisplayed()));
    }
}
