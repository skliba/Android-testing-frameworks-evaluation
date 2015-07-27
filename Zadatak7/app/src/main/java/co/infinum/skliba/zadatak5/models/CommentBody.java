package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 27.07.15..
 */
public class CommentBody implements Serializable{

    @SerializedName("comment")
    public CommentBodyContent comment;

    public void setComment(CommentBodyContent comment) {
        this.comment = comment;
    }
}
