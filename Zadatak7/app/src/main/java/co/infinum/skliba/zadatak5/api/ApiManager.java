package co.infinum.skliba.zadatak5.api;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.Executor;

import co.infinum.skliba.zadatak5.deserializers.UserAuthorizedDeserializer;
import co.infinum.skliba.zadatak5.enums.UserAuthorized;
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

    private static final Gson GSON = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaredClass().equals(ModelAdapter.class);
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    })
            .registerTypeAdapter(UserAuthorized.class, new UserAuthorizedDeserializer())
            .setDateFormat("yyyy-mm-dd'T'HH:mm:ss.SSSZ")
            .create();

    private static final CookieHandler COOKIE_HANDLER = new CookieManager();

    private BoatsService service;

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().setCookieHandler(COOKIE_HANDLER);

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };

    private static ApiManager instance;

    public synchronized static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    public void init() {
        OkHttpClient okHttpClient = new OkHttpClient().setCookieHandler(new CookieManager());
        setup(null, null, new OkClient(okHttpClient));
    }

    public void init(Executor httpExecutor, Executor callbackExecutor, OkClient okClient) {
        setup(httpExecutor, callbackExecutor, okClient);
    }

    private void setup(Executor httpExecutor, Executor callbackExecutor, OkClient okClient) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setClient(okClient)
                .setEndpoint(API_ENDPOINT)
                .setRequestInterceptor(new Interceptor())
                .setConverter(new GsonConverter(GSON))
                .setLog(LOG)
                .setLogLevel(RestAdapter.LogLevel.FULL);

        if (httpExecutor != null && callbackExecutor != null) {
            builder.setExecutors(httpExecutor, callbackExecutor);
        }

        service = builder.build().create(BoatsService.class);
    }

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setLog(LOG)
            .setClient(new OkClient(OK_HTTP_CLIENT))
            .setEndpoint(API_ENDPOINT)
            .setConverter(new GsonConverter(GSON))
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final BoatsService BOATS_SERVICE = REST_ADAPTER.create(BoatsService.class);

    public static BoatsService getService() {
        return BOATS_SERVICE;
    }

    public BoatsService getDetailsService(){
        return service;
    }
}
