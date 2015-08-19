package co.infinum.skliba.zadatak5.api;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import java.util.List;
import co.infinum.skliba.zadatak5.interfaces.DatabaseManager;
import co.infinum.skliba.zadatak5.models.boats.Post;


/**
 * Created by noxqs on 22.07.15..
 */
public class DbFlowPosts implements DatabaseManager {

    @Override
    public List<Post> getPosts() {
        return new Select().from(Post.class).queryList();
    }

    @Override
    public void addPost(Post post) {
        post.save();
    }

    @Override
    public void deletePost(Post post) {
        post.delete();
    }

    @Override
    public void dropAllPosts() {
        Delete.table(Post.class);
    }

}
