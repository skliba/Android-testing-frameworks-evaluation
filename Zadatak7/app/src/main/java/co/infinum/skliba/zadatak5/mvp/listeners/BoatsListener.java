package co.infinum.skliba.zadatak5.mvp.listeners;

import java.util.ArrayList;

import co.infinum.skliba.zadatak5.models.boats.Post;

/**
 * Created by noxqs on 27.07.15..
 */
public interface BoatsListener {

    void onBoatsRecieved(ArrayList<Post> postList);

    void onError(String error);
}
