package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 27.07.15..
 */
public class CreateCommentResponse implements Serializable{

    @SerializedName("response")
    private CreateCommentResponseBody response;

    public CreateCommentResponseBody getResponse() {
        return response;
    }
}
