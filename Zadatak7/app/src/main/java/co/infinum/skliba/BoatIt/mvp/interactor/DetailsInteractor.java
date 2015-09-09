package co.infinum.skliba.BoatIt.mvp.interactor;

import co.infinum.skliba.BoatIt.models.boats.Post;
import co.infinum.skliba.BoatIt.mvp.listeners.DetailsListener;

/**
 * Created by noxqs on 26.07.15..
 */
public interface DetailsInteractor {

    void getDetails(DetailsListener listener);

    void getComments(DetailsListener listener, Post post, String token);

    void onDownboat(DetailsListener listener, Post post);

    void onUpboat(DetailsListener listener, Post post);
}
