package co.infinum.skliba.BoatIt.mvp.interactor;

import co.infinum.skliba.BoatIt.mvp.listeners.BoatsListener;

/**
 * Created by noxqs on 27.07.15..
 */
public interface BoatsInteractor {

    void getBoats(BoatsListener listener, String token);
}
