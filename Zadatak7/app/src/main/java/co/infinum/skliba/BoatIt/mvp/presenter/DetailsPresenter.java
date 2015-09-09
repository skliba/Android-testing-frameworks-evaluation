package co.infinum.skliba.BoatIt.mvp.presenter;

import co.infinum.skliba.BoatIt.models.boats.Post;

/**
 * Created by noxqs on 26.07.15..
 */
public interface DetailsPresenter {

    void getDetails(Post post);

    void getCommentsPerPost(Post post, String token);

    void onUpboatClicked(Post post);

    void onDownboatClicked(Post post);

}
