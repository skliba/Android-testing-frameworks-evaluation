package co.infinum.skliba.zadatak5.mvp.presenter.impl;

import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.models.Post;
import co.infinum.skliba.zadatak5.mvp.interactor.DetailsInteractor;
import co.infinum.skliba.zadatak5.mvp.listeners.DetailsListener;
import co.infinum.skliba.zadatak5.mvp.presenter.DetailsPresenter;
import co.infinum.skliba.zadatak5.mvp.view.DetailsView;

/**
 * Created by noxqs on 26.07.15..
 */
public class DetailsPresenterImpl implements DetailsPresenter {

    private DetailsView view;
    private DetailsInteractor detailsInteractor;


    public DetailsPresenterImpl(DetailsView view, DetailsInteractor detailsInteractor) {
        this.view = view;
        this.detailsInteractor = detailsInteractor;
    }

    @Override
    public void getDetails(Post post) {
        detailsInteractor.getDetails(listener);
    }

    private DetailsListener listener = new DetailsListener() {
        @Override
        public void onDetailsRecieved(Post post) {
            view.onDetailsRecieved(post);
        }

        @Override
        public void onTokenExpired() {
            view.onTokenExpired();
        }

        @Override
        public void onError(String error) {
            view.showError(R.string.PostDetailsError);
        }
    };

    @Override
    public void onUpboatClicked() {

    }

    @Override
    public void onDownboatClicked() {

    }
}
