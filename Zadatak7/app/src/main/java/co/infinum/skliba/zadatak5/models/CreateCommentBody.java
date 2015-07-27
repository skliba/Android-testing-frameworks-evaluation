package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 27.07.15..
 */
public class CreateCommentBody implements Serializable{

    @SerializedName("comment")
    public CreateCommentBodyContent comment;

    public void setComment(CreateCommentBodyContent comment) {
        this.comment = comment;
    }
}
