package co.infinum.skliba.BoatIt.mvp.interactor.impl;

import android.content.Context;

import co.infinum.skliba.BoatIt.api.PostsApplication;
import co.infinum.skliba.BoatIt.helpers.SharedPrefsHelper;
import co.infinum.skliba.BoatIt.mvp.interactor.TokenInteractor;

/**
 * Created by noxqs on 29.07.15..
 */
public class TokenInteractorImpl implements TokenInteractor{

    private Context context;

    @Override
    public boolean userExists() {
        return getToken() != null;
    }

    @Override
    public String getToken() {
        context = PostsApplication.getMyContext();
        return SharedPrefsHelper.getToken(context);
    }
}
