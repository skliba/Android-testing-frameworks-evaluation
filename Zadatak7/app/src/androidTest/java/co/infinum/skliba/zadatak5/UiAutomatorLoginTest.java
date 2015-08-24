package co.infinum.skliba.zadatak5;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;


/**
 * Created by noxqs on 24.08.15..
 */
public class UiAutomatorLoginTest extends InstrumentationTestCase {

    private UiDevice device;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        device = UiDevice.getInstance(getInstrumentation());

        device.pressHome();

        UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));

        allAppsButton.clickAndWaitForNewWindow();

        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

        appViews.setAsHorizontalList();

        UiObject boatItApp = appViews.getChildByText(new UiSelector()
                        .className(android.widget.TextView.class.getName()),
                "Zadatak5");

        boatItApp.clickAndWaitForNewWindow();

        UiObject validation = new UiObject(new UiSelector()
                .packageName("co.infinum.skliba.zadatak5"));

        assertTrue("Unable to find boatIt", validation.exists());

        testLogin();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

    }

    public void testLogin() {

        UiObject loginButton = device.findObject(new UiSelector()
                .text("LOGIN")
                .className("android.widget.Button"));

        UiObject usernameEditText = device.findObject(new UiSelector()
                .resourceId("co.infinum.skliba.zadatak5:id/login_username")
                .className("android.widget.EditText"));

        UiObject passwordEditText = device.findObject(new UiSelector()
                .resourceId("co.infinum.skliba.zadatak5:id/login_password")
                .className("android.widget.EditText"));

        try {
            usernameEditText.setText("admin@infinum.co");
            passwordEditText.setText("infinum1");
            loginButton.click();

            UiObject recyclerViewList = device.findObject(new UiSelector()
                    .resourceId("co.infinum.skliba.zadatak5:id/list_view")
                    .className("android.support.v7.widget.RecyclerView"));

            //zbog iduce linije znamo da testovi prolaze, ukoliko to bude trebalo dokazat na obrani, stavit će se ! ispred recyclerViewList.exists(); onda bi trebao
            //test baciti error

            assertTrue("Package name wrong", recyclerViewList.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
}
