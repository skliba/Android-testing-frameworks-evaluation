package co.infinum.skliba.zadatak5.mvp.interactor.impl;

import co.infinum.skliba.zadatak5.api.PostsApplication;
import co.infinum.skliba.zadatak5.models.boats.BoatsResponse;
import co.infinum.skliba.zadatak5.mvp.interactor.BoatsInteractor;
import co.infinum.skliba.zadatak5.mvp.listeners.BoatsListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by noxqs on 27.07.15..
 */
public class BoatsInteractorImpl implements BoatsInteractor {

    private BoatsListener listener;

    @Override
    public void getBoats(BoatsListener listener, String token) {
        this.listener = listener;

        PostsApplication.getApiService().getAllBoats(token, boatsResponseCallback);
    }

    private Callback<BoatsResponse> boatsResponseCallback = new Callback<BoatsResponse>() {
        @Override
        public void success(BoatsResponse boatsResponse, Response response) {
            listener.onBoatsRecieved(boatsResponse.getRespose());
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onError(error.getMessage());
        }
    };
}
