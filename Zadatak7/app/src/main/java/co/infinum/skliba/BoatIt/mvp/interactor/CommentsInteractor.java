package co.infinum.skliba.BoatIt.mvp.interactor;

import co.infinum.skliba.BoatIt.mvp.listeners.CommentsListener;

/**
 * Created by noxqs on 27.07.15..
 */
public interface CommentsInteractor {

    void getComments(String post_id, String token);

    void addComment(CommentsListener listener, String post_id, String token, String content);

}
