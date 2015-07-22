package co.infinum.skliba.zadatak5.interfaces;

import java.util.ArrayList;
import java.util.List;

import co.infinum.skliba.zadatak5.models.BoatsResponse;
import co.infinum.skliba.zadatak5.models.Post;

/**
 * Created by noxqs on 22.07.15..
 */
public interface DatabaseManager {

    List<Post> getPosts();

    void addPost(Post post);

    void deletePost(Post post);

    void dropAllPosts();
}
