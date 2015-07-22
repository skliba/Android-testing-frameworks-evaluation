package co.infinum.skliba.zadatak5.api;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by noxqs on 22.07.15..
 */
public class PostsApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }
}
