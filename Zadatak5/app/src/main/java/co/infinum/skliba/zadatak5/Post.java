package co.infinum.skliba.zadatak5;

import com.google.gson.annotations.SerializedName;

/**
 * Created by noxqs on 18.07.15..
 */
public class Post {

    @SerializedName("id")
    private static final int id = -1;

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
