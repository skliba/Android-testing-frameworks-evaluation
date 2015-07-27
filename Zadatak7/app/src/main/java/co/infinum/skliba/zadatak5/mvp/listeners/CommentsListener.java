package co.infinum.skliba.zadatak5.mvp.listeners;


/**
 * Created by noxqs on 27.07.15..
 */
public interface CommentsListener {

    void onCreateCommentSuccess(String content);

    void onTokenExpired();

    void onCreateFailed(String error);

}
