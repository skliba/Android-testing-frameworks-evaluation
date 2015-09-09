package co.infinum.skliba.BoatIt.mvp.interactor;

import co.infinum.skliba.BoatIt.mvp.listeners.LoginListener;

/**
 * Created by noxqs on 27.07.15..
 */
public interface LoginInteractor {

    void login(LoginListener listener, String username, String password);
}
