package co.infinum.skliba.zadatak5.mvp.presenter.impl;

import co.infinum.skliba.zadatak5.models.LoginBody;
import co.infinum.skliba.zadatak5.models.LoginResponse;
import co.infinum.skliba.zadatak5.mvp.interactor.LoginInteractor;
import co.infinum.skliba.zadatak5.mvp.interactor.impl.LoginInteractorImpl;
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

    @Override
    public void login(LoginBody body) {
        interactor.login(loginListener, body);
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
}
