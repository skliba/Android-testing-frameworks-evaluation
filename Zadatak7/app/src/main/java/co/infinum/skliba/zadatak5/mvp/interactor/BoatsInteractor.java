package co.infinum.skliba.zadatak5.mvp.interactor;

import co.infinum.skliba.zadatak5.mvp.listeners.BoatsListener;

/**
 * Created by noxqs on 27.07.15..
 */
public interface BoatsInteractor {

    void getBoats(BoatsListener listener, String token);
}
