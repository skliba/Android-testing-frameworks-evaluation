package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by noxqs on 18.07.15..
 */
public class BoatsResponse implements Serializable{

    @SerializedName("response")
    private ArrayList<Post> postsList;

    public ArrayList<Post> getRespose() {
        return postsList;
    }
}
