package co.infinum.skliba.BoatIt.tests;

import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.io.IOException;

import co.infinum.skliba.BoatIt.R;
import co.infinum.skliba.BoatIt.activities.LoginActivity;
import co.infinum.skliba.BoatIt.network.TestApiManager;
import co.infinum.skliba.BoatIt.TestPostsApplication;
import co.infinum.skliba.BoatIt.utils.ResourceUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by noxqs on 29.07.15..
 */

@Config(sdk = 21, application = TestPostsApplication.class)
@RunWith(RobolectricTestRunner.class)
public class LoginTest {

    private MockWebServer mockWebServer;

    @Before
    public void setUp() {
        mockWebServer = TestApiManager.getInstance().getMockWebServer();
    }

    @After
    public void tearDown() {
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void successfulLogin() {
        mockWebServer.enqueue(new MockResponse()
        .setResponseCode(200)
        .setBody(ResourceUtils.readFromFile("login.json")));

        ActivityController<LoginActivity> loginActivityActivityController = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity loginActivity = loginActivityActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        EditText usernameEditText = (EditText) loginActivity.findViewById(R.id.login_username);
        EditText passwordEditText = (EditText) loginActivity.findViewById(R.id.login_password);
        Button loginButton = (Button) loginActivity.findViewById(R.id.login_button);

        usernameEditText.setText("admin@infinum.co");
        passwordEditText.setText("infinum1");
        loginButton.performClick();

        try{
            RecordedRequest recordedRequest = mockWebServer.takeRequest();
            assertThat(recordedRequest.getHeader("Content-type"), equalTo("application/json; charset=UTF-8"));
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
