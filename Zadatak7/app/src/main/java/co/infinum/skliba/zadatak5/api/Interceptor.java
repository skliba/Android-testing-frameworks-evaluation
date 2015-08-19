package co.infinum.skliba.zadatak5.api;

import android.text.TextUtils;

import co.infinum.skliba.zadatak5.helpers.SharedPrefsHelper;
import retrofit.RequestInterceptor;

/**
 * Created by noxqs on 26.07.15..
 */
public class Interceptor implements RequestInterceptor {

    private static final String TOKEN = "token";

    @Override
    public void intercept(RequestFacade request) {
        String token = SharedPrefsHelper.getToken(PostsApplication.getInstance());
        if (!TextUtils.isEmpty(token)) {
            request.addQueryParam(TOKEN, token);
        }
    }
}
