package co.infinum.skliba.zadatak5.models.comments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 27.07.15..
 */
public class CommentsResponseBody implements Serializable {

    @SerializedName("content")
    public String content;

    @SerializedName("author")
    public CommentAuthor author;

    public String getContent() {
        return content;
    }

    public CommentAuthor getAuthor() {
        return author;
    }
}
