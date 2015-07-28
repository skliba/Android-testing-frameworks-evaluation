package co.infinum.skliba.zadatak5.mvp.interactor;

import co.infinum.skliba.zadatak5.models.login.LoginBody;
import co.infinum.skliba.zadatak5.mvp.listeners.LoginListener;

/**
 * Created by noxqs on 27.07.15..
 */
public interface LoginInteractor {

    void login(LoginListener listener, LoginBody body);
}
