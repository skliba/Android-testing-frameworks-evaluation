package co.infinum.skliba.BoatIt;

import co.infinum.skliba.BoatIt.api.PostsApplication;
import co.infinum.skliba.BoatIt.network.TestApiManager;

/**
 * Created by noxqs on 29.07.15..
 */
public class TestPostsApplication extends PostsApplication {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void init() {
        TestApiManager.getInstance().init();

        apiManager = TestApiManager.getInstance();
    }
}
