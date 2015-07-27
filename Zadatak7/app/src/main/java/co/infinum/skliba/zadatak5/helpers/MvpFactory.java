package co.infinum.skliba.zadatak5.helpers;

import co.infinum.skliba.zadatak5.mvp.interactor.impl.CommentsInteractorImpl;
import co.infinum.skliba.zadatak5.mvp.interactor.impl.DetailsInteractorImpl;
import co.infinum.skliba.zadatak5.mvp.presenter.CommentPresenter;
import co.infinum.skliba.zadatak5.mvp.presenter.DetailsPresenter;
import co.infinum.skliba.zadatak5.mvp.presenter.impl.CommentPresenterImpl;
import co.infinum.skliba.zadatak5.mvp.presenter.impl.DetailsPresenterImpl;
import co.infinum.skliba.zadatak5.mvp.view.CommentsView;
import co.infinum.skliba.zadatak5.mvp.view.DetailsView;

/**
 * Created by noxqs on 27.07.15..
 */
public class MvpFactory{

    public static CommentPresenter getPresenter(CommentsView view){
        return new CommentPresenterImpl(view, new CommentsInteractorImpl());
    }

    public static DetailsPresenter getPresenter(DetailsView view){
        return new DetailsPresenterImpl(view, new DetailsInteractorImpl());
    }
}
