package co.infinum.skliba.zadatak5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.adapters.UserDialog;
import co.infinum.skliba.zadatak5.helpers.MvpFactory;
import co.infinum.skliba.zadatak5.models.login.LoginBody;
import co.infinum.skliba.zadatak5.models.login.LoginResponse;
import co.infinum.skliba.zadatak5.mvp.presenter.LoginPresenter;
import co.infinum.skliba.zadatak5.mvp.view.LoginView;


public class LoginActivity extends AppCompatActivity implements LoginView {

    public static final String TOKEN = "TOKEN";
    public static final String USER_DIALOG = "user-dialog";
    public static final String YOUFAILED = "YOUFAILED";

    private LoginBody user;

    @Bind(R.id.btn_register)
    Button btnRegister;

    @Bind(R.id.login_username)
    EditText loginUsername;

    @Bind(R.id.login_password)
    EditText loginPassword;

    @Bind(R.id.login_button)
    Button loginButton;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = MvpFactory.getPresenter(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillObject();
            }
        });


    }

    private void fillObject() {
        user = new LoginBody();
        user.setEmail(loginUsername.getText().toString());
        user.setPassword(loginPassword.getText().toString());

        presenter.login(user);
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClick(){
        UserDialog.newInstance(getString(R.string.registerDialog))
                .show(getSupportFragmentManager(), USER_DIALOG);
    }


    @Override
    public void showError(@StringRes int error) {

    }

    @Override
    public void onLogin(LoginResponse response) {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                .putString(TOKEN, response.getResponse().getToken()).apply();
        Intent intent = new Intent(LoginActivity.this, BoatsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }
}
