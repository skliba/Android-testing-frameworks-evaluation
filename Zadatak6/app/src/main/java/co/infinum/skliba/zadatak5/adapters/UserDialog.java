package co.infinum.skliba.zadatak5.adapters;

import android.app.Activity;
import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.api.ApiManager;
import co.infinum.skliba.zadatak5.models.RegisterDataUser;
import co.infinum.skliba.zadatak5.models.LoginResponse;
import co.infinum.skliba.zadatak5.models.RegisterData;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by noxqs on 22.07.15..
 */
public class UserDialog extends DialogFragment {

    private static final String TITLE_KEY = "title";

    private static final String USER_KEY = "user";

    private RegisterDataUser registerDataUser;

    private Context context;

    private static RegisterData registerData;

    @Bind(R.id.et_first_name)
    EditText etFirstName;

    @Bind(R.id.et_last_name)
    EditText etLastName;

    @Bind(R.id.et_email)
    EditText etEmail;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.et_password_repeat)
    EditText etPasswordRepeat;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    public static UserDialog newInstance(String title) {
        return newInstance(title, null);
    }

    public static UserDialog newInstance(String title, RegisterDataUser userData) {
        UserDialog fragment = new UserDialog();
        fragment.setStyle(android.support.v4.app.DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog_Alert);
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        args.putSerializable(USER_KEY, userData);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View contentView = getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, null);
        ButterKnife.bind(this, contentView);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString(TITLE_KEY));
        builder.setView(contentView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (validateUserInput()) {
                    fillObjectWithData();
                    registerData = new RegisterData();
                    registerData.setUser(registerDataUser);
                    ApiManager.getService().register(registerData, new Callback<LoginResponse>() {
                        @Override
                        public void success(LoginResponse loginResponse, Response response) {
                            Toast.makeText(context, "Register successful, you may log in with your new info", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e("FAILED", registerData.toString());
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();

                            Log.e("FAILED", gson.toJson(registerData));
                            Log.e("FAILED", error.getMessage());
                        }
                    });
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getApplicationContext(), "Clicked cancel", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }

    private void fillObjectWithData() {
        registerDataUser = new RegisterDataUser();
        registerDataUser.setEmail(etEmail.getText().toString());
        registerDataUser.setPassword(etPassword.getText().toString());
        registerDataUser.setPasswordRepeat(etPasswordRepeat.getText().toString());
        registerDataUser.setFirstName(etFirstName.getText().toString());
        registerDataUser.setLastName(etLastName.getText().toString());
    }

    private boolean validateUserInput() {

        if (!(etFirstName.getText().toString().isEmpty() || etLastName.getText().toString().isEmpty() ||
                etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty() || etPasswordRepeat.getText().toString().isEmpty())) {
            if(etPassword.getText().toString().equals(etPasswordRepeat.getText().toString())){
                return true;
            }
            else{
                Toast.makeText(getActivity().getApplicationContext(), "Password and retyped password do not match", Toast.LENGTH_LONG).show();
            }
        }

        Toast.makeText(getActivity().getApplicationContext(), "You didn't enter all the required fields", Toast.LENGTH_LONG).show();
        return false;
    }

}
