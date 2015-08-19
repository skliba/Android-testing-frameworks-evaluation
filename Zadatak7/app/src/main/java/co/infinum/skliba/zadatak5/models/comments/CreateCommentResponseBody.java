package co.infinum.skliba.zadatak5.models.comments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 27.07.15..
 */
public class CreateCommentResponseBody implements Serializable{

    @SerializedName("id")
    private long id;

    @SerializedName("content")
    private String content;

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
