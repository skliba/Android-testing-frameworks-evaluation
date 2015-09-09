package co.infinum.skliba.BoatIt.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import java.io.IOException;
import java.net.CookieManager;
import java.util.concurrent.Executor;

import co.infinum.skliba.BoatIt.api.ApiManager;
import co.infinum.skliba.BoatIt.api.Interceptor;
import co.infinum.skliba.BoatIt.deserializers.UserAuthorizedDeserializer;
import co.infinum.skliba.BoatIt.enums.UserAuthorized;
import co.infinum.skliba.BoatIt.interfaces.BoatsService;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by noxqs on 29.07.15..
 */
public class TestApiManager implements ApiManager{


    private static final String TAG = "Network";

    public static final String API_ENDPOINT = "https://boatit.infinum.co";

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(UserAuthorized.class, new UserAuthorizedDeserializer())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };

    private MockWebServer mockWebServer;

    private static TestApiManager instance;

    private BoatsService service;

    public synchronized static TestApiManager getInstance(){
        if(instance == null){
            instance = new TestApiManager();
        }
        return instance;
    }

    public void init(){
        OkHttpClient okHttpClient = new OkHttpClient().setCookieHandler(new CookieManager());
        setup(new SingleThreadExecutor(), new SingleThreadExecutor(), new OkClient(okHttpClient));
    }

    private void setup(Executor httpExecutor, Executor callbackExecutor, OkClient okClient){
        mockWebServer = new MockWebServer();

        try{
            mockWebServer.start();
        }catch(IOException e){
            e.printStackTrace();
        }

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setClient(okClient)
                .setEndpoint(mockWebServer.getUrl("/").toString())
                .setRequestInterceptor(new Interceptor())
                .setConverter(new GsonConverter(gson))
                .setLog(LOG)
                .setLogLevel(RestAdapter.LogLevel.FULL);

        if(httpExecutor != null && callbackExecutor != null){
            builder.setExecutors(httpExecutor, callbackExecutor);
        }

        service = builder.build().create(BoatsService.class);

    }


    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }

    @Override
    public BoatsService getService() {
        return service;
    }
}
