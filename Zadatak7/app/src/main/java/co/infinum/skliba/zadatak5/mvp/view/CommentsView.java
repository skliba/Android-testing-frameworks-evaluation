package co.infinum.skliba.zadatak5.mvp.view;

/**
 * Created by noxqs on 27.07.15..
 */
public interface CommentsView extends BaseView{

    void onCommentsRecieved();

    void onTokenExpired();

    void onCommentCreated();

    void onCreateFailed();
}
