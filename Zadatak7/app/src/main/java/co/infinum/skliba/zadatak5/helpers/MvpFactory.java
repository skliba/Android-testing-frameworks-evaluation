package co.infinum.skliba.zadatak5.helpers;

import co.infinum.skliba.zadatak5.mvp.interactor.impl.CommentsInteractorImpl;
import co.infinum.skliba.zadatak5.mvp.presenter.CommentPresenter;
import co.infinum.skliba.zadatak5.mvp.presenter.impl.CommentPresenterImpl;
import co.infinum.skliba.zadatak5.mvp.view.CommentsView;

/**
 * Created by noxqs on 27.07.15..
 */
public class MvpFactory{

    public static CommentPresenter getPresenter(CommentsView view){
        return new CommentPresenterImpl(view, new CommentsInteractorImpl());
    }
}
