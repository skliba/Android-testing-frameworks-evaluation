package co.infinum.skliba.zadatak5.mvp.presenter.impl;

import android.util.Log;

import co.infinum.skliba.zadatak5.models.boats.Post;
import co.infinum.skliba.zadatak5.mvp.interactor.CommentsInteractor;
import co.infinum.skliba.zadatak5.mvp.listeners.CommentsListener;
import co.infinum.skliba.zadatak5.mvp.presenter.CommentPresenter;
import co.infinum.skliba.zadatak5.mvp.view.CommentsView;

/**
 * Created by noxqs on 27.07.15..
 */
public class CommentPresenterImpl implements CommentPresenter {

    private CommentsView view;
    private CommentsInteractor interactor;

    public CommentPresenterImpl(CommentsView view, CommentsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getComments(Post post) {

    }

    @Override
    public void addComment(Post post, String token, String content) {
        Log.e("CommentPresenterImpl", "Add comment metoda");
        interactor.addComment(commentsListener, String.valueOf(post.id), token, content);

    }

    private CommentsListener commentsListener = new CommentsListener() {
        @Override
        public void onCreateCommentSuccess(String content) {
            view.onCommentCreated();
        }

        @Override
        public void onTokenExpired() {

        }

        @Override
        public void onCreateFailed(String error) {
            view.onCreateFailed();
        }
    };
}
