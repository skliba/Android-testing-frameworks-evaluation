package co.infinum.skliba.zadatak5;

import co.infinum.skliba.zadatak5.api.PostsApplication;
import co.infinum.skliba.zadatak5.network.TestApiManager;

/**
 * Created by noxqs on 29.07.15..
 */
public class TestPostsApplication extends PostsApplication {

    @Override
    protected void init() {
        TestApiManager.getInstance().init();

        apiManager = TestApiManager.getInstance();
    }
}
