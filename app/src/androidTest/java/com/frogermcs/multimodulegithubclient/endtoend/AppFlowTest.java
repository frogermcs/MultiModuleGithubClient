package com.frogermcs.multimodulegithubclient.endtoend;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.frogermcs.multimodulegithubclient.R;
import com.frogermcs.multimodulegithubclient.SplashActivity;
import com.frogermcs.multimodulegithubclient.repositories.RepositoriesListActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AppFlowTest {
    @Rule
    public IntentsTestRule<SplashActivity> splashActivityRule = new IntentsTestRule<>(SplashActivity.class);

    @Test
    public void goThroughAllScreensHappyPath() {
        onView(withHint("username")).perform(typeText("frogermcs"));
        onView(withText("Show repositories")).perform(click());

        // Dirty 'Tread.sleep()' replacement. The solution can be way more elegant here. :)
        onView(isRoot()).perform(waitFor(2000));

        // Is Activity started ?
        intended(hasComponent(RepositoriesListActivity.class.getName()));

        onView(withId(R.id.rvRepositories)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        onView(isRoot()).perform(waitFor(500));

        // Is Activity started? This time Activity isn't a direct part of app module,
        // so class cannot be pointed directly.
        intended(hasComponent("com.frogermcs.multimodulegithubclient.repository.RepositoryDetailsActivity"));
    }

    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}
