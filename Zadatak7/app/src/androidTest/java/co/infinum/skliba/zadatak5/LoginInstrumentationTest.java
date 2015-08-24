package co.infinum.skliba.zadatak5;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import co.infinum.skliba.zadatak5.activities.LoginActivity;

/**
 * Created by noxqs on 20.08.15..
 */
public class LoginInstrumentationTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Activity activity;

    public LoginInstrumentationTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testSuccessfulLogin() {
        Espresso.onView(ViewMatchers.withId(R.id.login_username))
                .perform(ViewActions.typeText("admin@infinum.co"));

        Espresso.onView(ViewMatchers.withId(R.id.login_password))
                .perform(
                        ViewActions.typeText("infinum1"),
                        ViewActions.closeSoftKeyboard()
                );

        Espresso.onView(ViewMatchers.isRoot())
                .perform(
                        ViewActions.swipeDown()
                );

        Espresso.onView(ViewMatchers.withText("LOGIN"))
                .perform(ViewActions.click());

        try {
            wait(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Espresso.onView(ViewMatchers.withId(R.id.list_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
