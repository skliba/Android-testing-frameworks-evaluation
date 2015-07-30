package co.infinum.skliba.zadatak5.mvp.presenter.impl;

import android.text.format.Time;

import java.util.ArrayList;

import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.models.UpboatDownboat.UpboatDownboatResponse;
import co.infinum.skliba.zadatak5.models.comments.CommentsResponse;
import co.infinum.skliba.zadatak5.models.boats.Post;
import co.infinum.skliba.zadatak5.models.comments.CommentsResponseBody;
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

    @Override
    public void getCommentsPerPost(Post post, String token) {
        detailsInteractor.getComments(listener, post, token);
    }

    @Override
    public void onUpboatClicked(Post post) {
        detailsInteractor.onUpboat(listener, post);
    }

    @Override
    public void onDownboatClicked(Post post) {
        detailsInteractor.onDownboat(listener, post);
    }


    private DetailsListener listener = new DetailsListener() {
        @Override
        public void onDetailsRecieved(Post post) {
            view.onDetailsRecieved(post);
        }

        @Override
        public void onCommentsRecieved(CommentsResponse response) {
            modifyTime(response);
            view.onCommentsRecieved(response.getResponse());
        }

        @Override
        public void onTokenExpired() {
            view.onTokenExpired();
        }

        @Override
        public void onError(String error) {
            view.showError(R.string.PostDetailsError);
        }

        @Override
        public void onUpboat(UpboatDownboatResponse response) {
            view.onUpboatSuccess();
        }

        @Override
        public void onDownboat(UpboatDownboatResponse response) {
            view.onDownboatSuccess();
        }
    };

    private void modifyTime(CommentsResponse response) {
        ArrayList<CommentsResponseBody> body = response.getResponse();

        for (CommentsResponseBody responseBody : body) {
            String time = responseBody.getTime();

            Time timeF = new Time();
            if (time != null) {
                if (timeF.parse3339(time)) {


                }
            }
        }
    }


}
