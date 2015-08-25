package co.infinum.skliba.zadatak5;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;


/**
 * Created by noxqs on 20.08.15..
 */
@SuppressWarnings("rawtypes")
public class RobotiumLoginInstrumentationTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;
    private static final String LOGIN_ACTIVITY_FULL_NAME = "co.infinum.skliba.zadatak5.activities.LoginActivity";

    private static Class<?> loginActivityClass;

    static {
        try {
            loginActivityClass = Class.forName(LOGIN_ACTIVITY_FULL_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public RobotiumLoginInstrumentationTest() throws ClassNotFoundException{
        super(loginActivityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testLogin(){

        solo.waitForActivity("LoginActivity", 2000);

        solo.clearEditText(0);
        solo.clearEditText(1);

        solo.enterText(((EditText) solo.getView(R.id.login_username)),  "admin@infinum.co");
        solo.enterText(((EditText) solo.getView(R.id.login_password)), "infinum1");

        solo.clickOnButton(0);

        assertTrue("RecyclerView not visible",  solo.getView(R.id.list_view).isShown());
    }


}
