package co.infinum.skliba.BoatIt.mvp.view;

import java.util.ArrayList;

import co.infinum.skliba.BoatIt.models.boats.Post;

/**
 * Created by noxqs on 27.07.15..
 */
public interface BoatsView extends BaseView {

    void onBoatsRecieved(ArrayList<Post> arrayList);

    void onTokenExpired();

}
