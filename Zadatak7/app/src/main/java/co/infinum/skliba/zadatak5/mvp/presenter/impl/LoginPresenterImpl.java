package co.infinum.skliba.zadatak5.mvp.presenter.impl;

import android.text.TextUtils;

import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.models.login.LoginBody;
import co.infinum.skliba.zadatak5.models.login.LoginResponse;
import co.infinum.skliba.zadatak5.mvp.interactor.LoginInteractor;
import co.infinum.skliba.zadatak5.mvp.listeners.LoginListener;
import co.infinum.skliba.zadatak5.mvp.presenter.LoginPresenter;
import co.infinum.skliba.zadatak5.mvp.view.LoginView;

/**
 * Created by noxqs on 27.07.15..
 */
public class LoginPresenterImpl implements LoginPresenter {


    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    private LoginListener loginListener = new LoginListener() {
        @Override
        public void onLogin(LoginResponse response) {
            view.onLogin(response);
        }

        @Override
        public void onError(String error) {
            view.onError(error);
        }
    };

    @Override
    public void login(String username, String password) {
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            interactor.login(loginListener, username, password);
        }
        else{
            view.showError(R.string.PostDetailsError);
        }

    }
}
