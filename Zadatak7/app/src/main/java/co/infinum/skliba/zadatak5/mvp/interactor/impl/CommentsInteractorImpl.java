package co.infinum.skliba.zadatak5.mvp.interactor.impl;

import android.util.Log;

import co.infinum.skliba.zadatak5.api.PostsApplication;
import co.infinum.skliba.zadatak5.models.CreateCommentBody;
import co.infinum.skliba.zadatak5.models.CreateCommentBodyContent;
import co.infinum.skliba.zadatak5.models.CreateCommentResponse;
import co.infinum.skliba.zadatak5.mvp.interactor.CommentsInteractor;
import co.infinum.skliba.zadatak5.mvp.listeners.CommentsListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by noxqs on 27.07.15..
 */
public class CommentsInteractorImpl implements CommentsInteractor {

    private CommentsListener listener;
    private CreateCommentBody commentBody;
    private CreateCommentBodyContent commentBodyContent;

    @Override
    public void getComments(String post_id, String token) {

    }

    @Override
    public void addComment(CommentsListener listener, String post_id, String token, String content) {
        this.listener = listener;
        Log.e("CommentsInteractorImpl", "add comment metoda i dalje");
        commentBody = new CreateCommentBody();
        commentBodyContent = new CreateCommentBodyContent();
        commentBodyContent.content = content;

        commentBody.setComment(commentBodyContent);
        Log.e("TOKEN", token);
        PostsApplication.getApiService().createAComment(post_id, token, commentBody , createCommentResponseCallback);
    }

    private Callback<CreateCommentResponse> createCommentResponseCallback = new Callback<CreateCommentResponse>() {
        @Override
        public void success(CreateCommentResponse createCommentResponse, Response response) {
            Log.e("CommentsInteractorImpl", "success metoda");
            listener.onCreateCommentSuccess(createCommentResponse.getResponse().getContent());
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("CommentsInteractorImpl", error.getMessage());
            listener.onCreateFailed(error.getMessage());
        }
    };
}
