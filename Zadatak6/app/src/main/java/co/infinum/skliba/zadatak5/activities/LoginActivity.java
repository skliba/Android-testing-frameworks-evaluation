package co.infinum.skliba.zadatak5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.api.ApiManager;
import co.infinum.skliba.zadatak5.adapters.UserDialog;
import co.infinum.skliba.zadatak5.models.LoginResponse;
import co.infinum.skliba.zadatak5.models.BodyUserData;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends AppCompatActivity {

    public static final String TOKEN = "TOKEN";
    public static final String USER_DIALOG = "user-dialog";

    private BodyUserData user;

    @Bind(R.id.btnRegister)
    Button btnRegister;

    @Bind(R.id.loginUsername)
    EditText loginUsername;

    @Bind(R.id.loginPassword)
    EditText loginPassword;

    @Bind(R.id.loginButton)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });


    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClick(){
        UserDialog.newInstance(getString(R.string.registerDialog))
                .show(getSupportFragmentManager(), USER_DIALOG);
    }

    private void checkCredentials() {
        user = new BodyUserData();
        user.setEmail(loginUsername.getText().toString());
        user.setPassword(loginPassword.getText().toString());
        ApiManager.getService().login(user, new Callback<LoginResponse>() {
            @Override
            public void success(LoginResponse response, Response response2) {
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
}
