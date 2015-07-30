package co.infinum.skliba.zadatak5.api;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;

import co.infinum.skliba.zadatak5.interfaces.BoatsService;

/**
 * Created by noxqs on 22.07.15..
 */
public class PostsApplication extends Application{

    private static PostsApplication instance;
    private static Context context;
    protected ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        PostsApplication.context = getApplicationContext();
        init();
        FlowManager.init(this);
    }

    protected void init() {
        ApiManagerImpl.getInstance().init();

        apiManager = ApiManagerImpl.getInstance();
    }

    public static PostsApplication getInstance(){
        return instance;
    }

    public static BoatsService getApiService() {
        return ApiManagerImpl.getInstance().getService();
    }

    public static Context getMyContext(){
        return context;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }
}
