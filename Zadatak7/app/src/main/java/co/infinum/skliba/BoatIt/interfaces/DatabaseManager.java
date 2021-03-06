package co.infinum.skliba.BoatIt.interfaces;

import java.util.List;

import co.infinum.skliba.BoatIt.models.boats.Post;

/**
 * Created by noxqs on 22.07.15..
 */
public interface DatabaseManager {

    List<Post> getPosts();

    void addPost(Post post);

    void deletePost(Post post);

    void dropAllPosts();
}
