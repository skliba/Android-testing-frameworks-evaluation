package co.infinum.skliba.zadatak5.mvp.presenter;

import co.infinum.skliba.zadatak5.models.boats.Post;

/**
 * Created by noxqs on 27.07.15..
 */
public interface CommentPresenter {

    void getComments(Post post);

    void addComment(Post post, String token, String content);
}
