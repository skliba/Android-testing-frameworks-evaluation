package co.infinum.skliba.BoatIt.mvp.presenter.impl;

import java.util.ArrayList;

import co.infinum.skliba.BoatIt.models.boats.Post;
import co.infinum.skliba.BoatIt.mvp.interactor.BoatsInteractor;
import co.infinum.skliba.BoatIt.mvp.listeners.BoatsListener;
import co.infinum.skliba.BoatIt.mvp.presenter.BoatsPresenter;
import co.infinum.skliba.BoatIt.mvp.view.BoatsView;

/**
 * Created by noxqs on 27.07.15..
 */
public class BoatsPresenterImpl implements BoatsPresenter {

    private BoatsView view;
    private BoatsInteractor interactor;

    public BoatsPresenterImpl(BoatsView view, BoatsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onFetchBoats(String token) {
        interactor.getBoats(listener, token);
    }

    private BoatsListener listener = new BoatsListener() {
        @Override
        public void onBoatsRecieved(ArrayList<Post> postList) {
            view.onBoatsRecieved(postList);
        }

        @Override
        public void onError(String error) {

        }

        @Override
        public void onTokenExpired() {
            view.onTokenExpired();
        }
    };
}
