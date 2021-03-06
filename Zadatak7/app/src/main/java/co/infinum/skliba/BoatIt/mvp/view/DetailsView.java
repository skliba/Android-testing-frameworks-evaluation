package co.infinum.skliba.BoatIt.mvp.view;

import java.util.ArrayList;

import co.infinum.skliba.BoatIt.models.comments.CommentsResponseBody;
import co.infinum.skliba.BoatIt.models.boats.Post;

/**
 * Created by noxqs on 26.07.15..
 */
public interface DetailsView extends BaseView{

    void onDetailsRecieved(Post post);

    void onTokenExpired();

    void onCommentsRecieved(ArrayList<CommentsResponseBody> response);

    void onUpboatSuccess();

    void onDownboatSuccess();

}
