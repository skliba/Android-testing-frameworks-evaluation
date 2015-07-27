package co.infinum.skliba.zadatak5.mvp.listeners;

import co.infinum.skliba.zadatak5.models.LoginResponse;

/**
 * Created by noxqs on 28.07.15..
 */
public interface LoginListener {

    void onLogin(LoginResponse response);

    void onError(String error);
}
