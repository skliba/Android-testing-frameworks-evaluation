package co.infinum.skliba.zadatak5.mvp.view;

import co.infinum.skliba.zadatak5.models.login.LoginResponse;

/**
 * Created by noxqs on 27.07.15..
 */
public interface LoginView extends BaseView{

    void onLogin(LoginResponse response);

    void onError(String error);
}
