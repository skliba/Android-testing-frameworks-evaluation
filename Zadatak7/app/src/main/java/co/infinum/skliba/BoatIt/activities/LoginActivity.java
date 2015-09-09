package co.infinum.skliba.BoatIt.activities;

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
import co.infinum.skliba.BoatIt.R;
import co.infinum.skliba.BoatIt.adapters.UserDialog;
import co.infinum.skliba.BoatIt.helpers.MvpFactory;
import co.infinum.skliba.BoatIt.models.login.LoginResponse;
import co.infinum.skliba.BoatIt.mvp.presenter.LoginPresenter;
import co.infinum.skliba.BoatIt.mvp.view.LoginView;


public class LoginActivity extends AppCompatActivity implements LoginView {

    public static final String TOKEN = "TOKEN";
    public static final String USER_DIALOG = "user-dialog";

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
                presenter.login(loginUsername.getText().toString(), loginPassword.getText().toString());
            }
        });
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
