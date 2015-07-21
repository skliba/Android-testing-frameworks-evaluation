package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 18.07.15..
 */
public class Post implements Serializable{

    @SerializedName("title")
    public String title;

    @SerializedName("image_url")
    public String imageUrl;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
