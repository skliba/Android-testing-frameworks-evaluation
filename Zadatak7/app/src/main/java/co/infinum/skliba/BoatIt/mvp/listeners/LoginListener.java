package co.infinum.skliba.BoatIt.mvp.listeners;

import co.infinum.skliba.BoatIt.models.login.LoginResponse;

/**
 * Created by noxqs on 28.07.15..
 */
public interface LoginListener {

    void onLogin(LoginResponse response);

    void onError(String error);
}
