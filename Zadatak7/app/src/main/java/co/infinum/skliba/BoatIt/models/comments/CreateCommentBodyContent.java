package co.infinum.skliba.BoatIt.models.comments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 27.07.15..
 */
public class CreateCommentBodyContent implements Serializable{

    @SerializedName("content")
    public String content;

    public String getContent() {
        return content;
    }
}
