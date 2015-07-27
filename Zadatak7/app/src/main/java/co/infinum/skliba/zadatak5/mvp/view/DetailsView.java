package co.infinum.skliba.zadatak5.mvp.view;

import co.infinum.skliba.zadatak5.models.Post;

/**
 * Created by noxqs on 26.07.15..
 */
public interface DetailsView extends BaseView{

    void onDetailsRecieved(Post post);

    void onTokenExpired();

}
