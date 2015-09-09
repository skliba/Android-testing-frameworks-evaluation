package co.infinum.skliba.BoatIt.api;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by noxqs on 22.07.15..
 */

@Database(name = PostsDatabase.NAME, version = PostsDatabase.VERSION)
public class PostsDatabase {

    public static final String NAME = "PostsDatabase";

    public static final int VERSION = 1;
}
