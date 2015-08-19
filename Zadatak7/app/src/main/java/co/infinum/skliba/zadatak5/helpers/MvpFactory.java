package co.infinum.skliba.zadatak5.helpers;

import co.infinum.skliba.zadatak5.mvp.interactor.impl.BoatsInteractorImpl;
import co.infinum.skliba.zadatak5.mvp.interactor.impl.CommentsInteractorImpl;
import co.infinum.skliba.zadatak5.mvp.interactor.impl.DetailsInteractorImpl;
import co.infinum.skliba.zadatak5.mvp.interactor.impl.LoginInteractorImpl;
import co.infinum.skliba.zadatak5.mvp.presenter.BoatsPresenter;
import co.infinum.skliba.zadatak5.mvp.presenter.CommentPresenter;
import co.infinum.skliba.zadatak5.mvp.presenter.DetailsPresenter;
import co.infinum.skliba.zadatak5.mvp.presenter.LoginPresenter;
import co.infinum.skliba.zadatak5.mvp.presenter.impl.BoatsPresenterImpl;
import co.infinum.skliba.zadatak5.mvp.presenter.impl.CommentPresenterImpl;
import co.infinum.skliba.zadatak5.mvp.presenter.impl.DetailsPresenterImpl;
import co.infinum.skliba.zadatak5.mvp.presenter.impl.LoginPresenterImpl;
import co.infinum.skliba.zadatak5.mvp.view.BoatsView;
import co.infinum.skliba.zadatak5.mvp.view.CommentsView;
import co.infinum.skliba.zadatak5.mvp.view.DetailsView;
import co.infinum.skliba.zadatak5.mvp.view.LoginView;

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

    public static BoatsPresenter getPresenter(BoatsView view){
        return new BoatsPresenterImpl(view, new BoatsInteractorImpl());
    }

    public static LoginPresenter getPresenter(LoginView view){
        return new LoginPresenterImpl(view, new LoginInteractorImpl());
    }
}
