package co.infinum.skliba.zadatak5.mvp.interactor.impl;

import android.util.Log;

import co.infinum.skliba.zadatak5.api.PostsApplication;
import co.infinum.skliba.zadatak5.models.comments.CommentsResponse;
import co.infinum.skliba.zadatak5.models.boats.Post;
import co.infinum.skliba.zadatak5.mvp.interactor.DetailsInteractor;
import co.infinum.skliba.zadatak5.mvp.listeners.DetailsListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by noxqs on 26.07.15..
 */
public class DetailsInteractorImpl implements DetailsInteractor{

    private DetailsListener detailsListener;

    @Override
    public void getDetails(DetailsListener listener) {
        this.detailsListener = listener;


    }

    @Override
    public void getComments(DetailsListener listener, Post post, String token) {
        this.detailsListener = listener;

        String id = " " + post.id;

        PostsApplication.getApiService().getAllComments(id, token, commentsResponseCallback);
    }

    private Callback<CommentsResponse> commentsResponseCallback = new Callback<CommentsResponse>() {
        @Override
        public void success(CommentsResponse commentsResponse, Response response) {
            detailsListener.onCommentsRecieved(commentsResponse);

        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("FETCHING COMMENTS ERROR", error.getMessage());
        }
    };
}
