package co.infinum.skliba.BoatIt;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.infinum.skliba.BoatIt.activities.LoginActivity;

/**
 * Created by noxqs on 20.08.15..
 */
public class LoginInstrumentationTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public LoginInstrumentationTest() {
        super(LoginActivity.class);
    }

    @Before
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Activity activity = getActivity();
    }

    @After
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

        try{
            Thread.sleep(5000);
            Espresso.onView(ViewMatchers.withId(R.id.login_username)).check(ViewAssertions.doesNotExist());
            Espresso.onView(ViewMatchers.withId(R.id.list_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            Espresso.onView(ViewMatchers.withId(R.id.list_view)).perform(ViewActions.click());
            Thread.sleep(2000);

            Espresso.onView(ViewMatchers.withId(R.id.details_picture)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            Espresso.onView(ViewMatchers.withId(R.id.button_downboat)).perform(ViewActions.click());

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }




    }

}