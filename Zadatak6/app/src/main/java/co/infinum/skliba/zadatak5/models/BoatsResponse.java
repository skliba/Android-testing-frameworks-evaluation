package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;

import co.infinum.skliba.zadatak5.api.PostsDatabase;

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
