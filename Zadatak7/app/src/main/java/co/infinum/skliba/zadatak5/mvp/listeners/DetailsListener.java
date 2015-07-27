package co.infinum.skliba.zadatak5.mvp.listeners;

import co.infinum.skliba.zadatak5.models.CommentsResponse;
import co.infinum.skliba.zadatak5.models.Post;

/**
 * Created by noxqs on 26.07.15..
 */
public interface DetailsListener {

    void onDetailsRecieved(Post post);

    void onCommentsRecieved(CommentsResponse response);

    void onTokenExpired();

    void onError(String error);
}
