package ru.gdgkazan.githubmvp.screen.walkthrough;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.gdgkazan.githubmvp.AppDelegate;
import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.di.component.DaggerTestAppComponent;
import ru.gdgkazan.githubmvp.screen.auth.AuthActivity;
import ru.gdgkazan.githubmvp.test.idling.TimeIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * @author Alexey Pakhtusov
 */
@RunWith(AndroidJUnit4.class)
public class WalkthroughActivityTest {

    @Rule
    public ActivityTestRule<WalkthroughActivity> activityRule =
            new ActivityTestRule<>(WalkthroughActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        /* Подменяем AppComponent на TestAppComponent.
         * Чтобы Dagger 2 смог сгенерировать DaggerTestAppComponent, нужно в build.gradle
         * указать (androidTestApt), что можно генерировать компоненты для androidTest.
         * Аналогично для Unit тестов в build.gradle нужно указывать (testApt),
         * что можно генерировать компоненты для test */
        AppDelegate.setAppComponent(DaggerTestAppComponent.create());
        Intents.init();
        activityRule.launchActivity(new Intent());
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
        AppDelegate.setAppComponent(null);
    }

    @Test
    public void firstBenefitVerify() throws Exception {
        onView(allOf(withId(R.id.benefitIcon), withDrawableId(R.drawable.cat1)))
                .check(matches(isCompletelyDisplayed()));
        onView(allOf(withId(R.id.benefitText), withText(R.string.benefit_work_together)))
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.btn_walkthrough))
                .check(matches(withText(R.string.next_uppercase)));
    }

    @Test
    public void secondBenefitVerify() throws Exception {
        onView(withId(R.id.btn_walkthrough)).perform(click());

        onView(allOf(withId(R.id.benefitIcon), withDrawableId(R.drawable.cat2)))
                .check(matches(isCompletelyDisplayed()));
        onView(allOf(withId(R.id.benefitText), withText(R.string.benefit_code_history)))
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.btn_walkthrough))
                .check(matches(withText(R.string.next_uppercase)));
    }

    @Test
    public void thirdBenefitVerify() throws Exception {
        onView(withId(R.id.btn_walkthrough)).perform(click(), click());

        onView(allOf(withId(R.id.benefitIcon), withDrawableId(R.drawable.cat3)))
                .check(matches(isCompletelyDisplayed()));
        onView(allOf(withId(R.id.benefitText), withText(R.string.benefit_publish_source)))
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.btn_walkthrough))
                .check(matches(withText(R.string.finish_uppercase)));
    }

    @Test
    public void showAuthView() throws Exception {
        onView(withId(R.id.btn_walkthrough)).perform(click(), click(), click());

        Intents.intended(hasComponent(AuthActivity.class.getName()));
    }

    @Test
    public void swipeLeftTest() throws Exception {
        onView(allOf(withId(R.id.benefitIcon), withDrawableId(R.drawable.cat1)))
                .check(matches(isCompletelyDisplayed()));
        onView(allOf(withId(R.id.benefitText), withText(R.string.benefit_work_together)))
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.btn_walkthrough))
                .check(matches(withText(R.string.next_uppercase)));

        onView(withId(R.id.pager)).perform(swipeLeft());

        IdlingResource idlingResource = TimeIdlingResource.timeout(500);
        onView(allOf(withId(R.id.benefitIcon), withDrawableId(R.drawable.cat2)))
                .check(matches(isCompletelyDisplayed()));
        onView(allOf(withId(R.id.benefitText), withText(R.string.benefit_code_history)))
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.btn_walkthrough))
                .check(matches(withText(R.string.next_uppercase)));
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void swipeLeftRightTest() throws Exception {
        onView(allOf(withId(R.id.benefitIcon), withDrawableId(R.drawable.cat1)))
                .check(matches(isCompletelyDisplayed()));
        onView(allOf(withId(R.id.benefitText), withText(R.string.benefit_work_together)))
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.btn_walkthrough))
                .check(matches(withText(R.string.next_uppercase)));

        onView(withId(R.id.pager)).perform(swipeLeft());
        onView(withId(R.id.pager)).perform(swipeRight());

        IdlingResource idlingResource = TimeIdlingResource.timeout(500);
        onView(allOf(withId(R.id.benefitIcon), withDrawableId(R.drawable.cat1)))
                .check(matches(isCompletelyDisplayed()));
        onView(allOf(withId(R.id.benefitText), withText(R.string.benefit_work_together)))
                .check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.btn_walkthrough))
                .check(matches(withText(R.string.next_uppercase)));
        Espresso.unregisterIdlingResources(idlingResource);
    }

    private TypeSafeMatcher<View> withDrawableId(@DrawableRes int drawableId) {
        return new TypeSafeMatcher<View>() {

            @Override
            protected boolean matchesSafely(View view) {
                if (view instanceof ImageView) {
                    if (activityRule.getActivity().getResources().getDrawable(drawableId).getConstantState()
                            .equals(((ImageView) view).getDrawable().getConstantState())) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public void describeTo(Description description) {
                // do nothing
            }
        };
    }
}
