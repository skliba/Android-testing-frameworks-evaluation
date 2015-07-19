package co.infinum.skliba.zadatak5;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by noxqs on 18.07.15..
 */
public class BoatsResponse {

    @SerializedName("response")
    private ArrayList<Post> postsList;

    public ArrayList<Post> getRespose() {
        return postsList;
    }
}
