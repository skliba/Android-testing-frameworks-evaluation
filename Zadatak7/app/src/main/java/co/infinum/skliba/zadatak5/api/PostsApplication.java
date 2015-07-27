package co.infinum.skliba.zadatak5.api;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

import co.infinum.skliba.zadatak5.interfaces.BoatsService;
import co.infinum.skliba.zadatak5.models.Post;

/**
 * Created by noxqs on 22.07.15..
 */
public class PostsApplication extends Application{

    private static PostsApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        FlowManager.init(this);
    }

    private void init() {
        ApiManager.getInstance().init();
    }

    public static PostsApplication getInstance(){
        return instance;
    }

    public static BoatsService getApiService() {
        return ApiManager.getInstance().getDetailsService();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }
}
