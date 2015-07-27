package co.infinum.skliba.zadatak5.mvp.interactor.impl;

import co.infinum.skliba.zadatak5.api.PostsApplication;
import co.infinum.skliba.zadatak5.interfaces.BoatsService;
import co.infinum.skliba.zadatak5.mvp.interactor.DetailsInteractor;
import co.infinum.skliba.zadatak5.mvp.listeners.DetailsListener;

/**
 * Created by noxqs on 26.07.15..
 */
public class DetailsInteractorImpl implements DetailsInteractor{

    private DetailsListener detailsListener;

    @Override
    public void getDetails(DetailsListener listener) {
        this.detailsListener = listener;


    }
}
