package co.infinum.skliba.zadatak5.mvp.presenter;

import co.infinum.skliba.zadatak5.models.Post;

/**
 * Created by noxqs on 26.07.15..
 */
public interface DetailsPresenter {

    void getDetails(Post post);

    void getCommentsPerPost(Post post, String token);

    void onUpboatClicked();

    void onDownboatClicked();

}
