package co.infinum.skliba.zadatak5.mvp.interactor.impl;

import android.preference.PreferenceManager;
import android.util.Log;

import co.infinum.skliba.zadatak5.api.PostsApplication;
import co.infinum.skliba.zadatak5.models.UpboatDownboat.UpboatDownboatResponse;
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

    public static final String TOKEN = "TOKEN";
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

    @Override
    public void onDownboat(DetailsListener listener, Post post) {
        String token = PreferenceManager.getDefaultSharedPreferences(PostsApplication.getMyContext()).getString(TOKEN, "");
        PostsApplication.getApiService().downboat("" + post.id, token, downboatResponseCallback);
    }

    @Override
    public void onUpboat(DetailsListener listener, Post post) {
        String token = PreferenceManager.getDefaultSharedPreferences(PostsApplication.getMyContext()).getString(TOKEN, "");
        PostsApplication.getApiService().upboat("" + post.id, token, upboatResponseCallback);
    }


    //callbacks
    //-------------------------------------------------------

    private Callback<CommentsResponse> commentsResponseCallback = new Callback<CommentsResponse>() {
        @Override
        public void success(CommentsResponse commentsResponse, Response response) {
            detailsListener.onCommentsRecieved(commentsResponse);

        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("FETCHING COMMENTS ERROR", error.getMessage());
            detailsListener.onError(error.getMessage());
        }
    };

    private Callback<UpboatDownboatResponse> upboatResponseCallback = new Callback<UpboatDownboatResponse>() {
        @Override
        public void success(UpboatDownboatResponse upboatResponse, Response response) {
            Log.e("UPBOAT", "" +  upboatResponse.getResponse().getScore());
            detailsListener.onUpboat(upboatResponse);
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("UPBOATERROR", error.getMessage());
            detailsListener.onError(error.getMessage());
        }
    };

    private Callback<UpboatDownboatResponse> downboatResponseCallback = new Callback<UpboatDownboatResponse>() {
        @Override
        public void success(UpboatDownboatResponse response, Response response2) {
            Log.e("DOWNBOAT", "" +  response.getResponse().getScore());
            detailsListener.onDownboat(response);
        }

        @Override
        public void failure(RetrofitError error) {
            detailsListener.onError(error.getMessage());
        }
    };

}
