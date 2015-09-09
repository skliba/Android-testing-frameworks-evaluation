package co.infinum.skliba.BoatIt.mvp.interactor.impl;

import android.util.Log;

import co.infinum.skliba.BoatIt.api.PostsApplication;
import co.infinum.skliba.BoatIt.models.login.LoginBody;
import co.infinum.skliba.BoatIt.models.login.LoginResponse;
import co.infinum.skliba.BoatIt.mvp.interactor.LoginInteractor;
import co.infinum.skliba.BoatIt.mvp.listeners.LoginListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by noxqs on 27.07.15..
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginListener loginListener;

    @Override
    public void login(LoginListener listener, String username, String password) {
        loginListener = listener;
        LoginBody body = new LoginBody(username, password);
        PostsApplication.getApiService().login(body, loginResponseCallback);
    }

    private Callback<LoginResponse> loginResponseCallback = new Callback<LoginResponse>() {
        @Override
        public void success(LoginResponse loginResponse, Response response) {
            loginListener.onLogin(loginResponse);
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("ERROR", error.getMessage());
            loginListener.onError(error.getMessage());
        }
    };


}
