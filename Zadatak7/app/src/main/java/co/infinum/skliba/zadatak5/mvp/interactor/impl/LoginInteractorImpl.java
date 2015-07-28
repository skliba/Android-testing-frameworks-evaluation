package co.infinum.skliba.zadatak5.mvp.interactor.impl;

import co.infinum.skliba.zadatak5.api.PostsApplication;
import co.infinum.skliba.zadatak5.models.login.LoginBody;
import co.infinum.skliba.zadatak5.models.login.LoginResponse;
import co.infinum.skliba.zadatak5.mvp.interactor.LoginInteractor;
import co.infinum.skliba.zadatak5.mvp.listeners.LoginListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by noxqs on 27.07.15..
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginListener listener;

    @Override
    public void login(LoginListener listener, LoginBody body) {
        this.listener = listener;

        PostsApplication.getApiService().login(body, loginResponseCallback);
    }

    private Callback<LoginResponse> loginResponseCallback = new Callback<LoginResponse>() {
        @Override
        public void success(LoginResponse loginResponse, Response response) {
            listener.onLogin(loginResponse);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onError(error.getMessage());
        }
    };
}
