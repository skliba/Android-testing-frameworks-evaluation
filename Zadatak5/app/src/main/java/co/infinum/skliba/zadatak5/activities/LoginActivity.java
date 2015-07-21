package co.infinum.skliba.zadatak5.activities;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.infinum.skliba.zadatak5.api.ApiManager;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.login.LoginResponse;
import co.infinum.skliba.zadatak5.login.User;
import retrofit.Callback;
import retrofit.RetrofitError;


public class LoginActivity extends ActionBarActivity {

    public static final String TOKEN = "TOKEN";
    private Button loginButton;
    private EditText loginUsername, loginPassword;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loginButton = (Button) findViewById(R.id.loginButton);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginUsername = (EditText) findViewById(R.id.loginUsername);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });


    }

    private void checkCredentials() {
        user = new User();
        user.setEmail(loginUsername.getText().toString());
        user.setPassword(loginPassword.getText().toString());
        ApiManager.getService().login(user, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse response, retrofit.client.Response response2) {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                        .putString(TOKEN, response.getResponse().getToken()).apply();
                Intent intent = new Intent(LoginActivity.this, BoatsActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("YOUFAILED", error.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
