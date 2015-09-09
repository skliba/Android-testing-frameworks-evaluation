package co.infinum.skliba.BoatIt.mvp.listeners;

import co.infinum.skliba.BoatIt.models.UpboatDownboat.UpboatDownboatResponse;
import co.infinum.skliba.BoatIt.models.comments.CommentsResponse;
import co.infinum.skliba.BoatIt.models.boats.Post;

/**
 * Created by noxqs on 26.07.15..
 */
public interface DetailsListener {

    void onDetailsRecieved(Post post);

    void onCommentsRecieved(CommentsResponse response);

    void onTokenExpired();

    void onError(String error);

    void onUpboat(UpboatDownboatResponse response);

    void onDownboat(UpboatDownboatResponse response);
}
