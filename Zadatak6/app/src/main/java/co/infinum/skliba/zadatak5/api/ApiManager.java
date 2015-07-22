package co.infinum.skliba.zadatak5.api;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;
import java.net.CookieManager;

import co.infinum.skliba.zadatak5.interfaces.BoatsService;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by noxqs on 18.07.15..
 */
public class ApiManager {

    private static final String TAG = "Network";

    public static final String API_ENDPOINT = "https://boatit.infinum.co";

    private static final CookieHandler COOKIE_HANDLER = new CookieManager();

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().setCookieHandler(COOKIE_HANDLER);

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };


    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setLog(LOG)
            .setClient(new OkClient(OK_HTTP_CLIENT))
            .setEndpoint(API_ENDPOINT)
            .setConverter(new GsonConverter(new Gson()))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final BoatsService BOATS_SERVICE = REST_ADAPTER.create(BoatsService.class);

    public static BoatsService getService() {
        return BOATS_SERVICE;
    }
}
