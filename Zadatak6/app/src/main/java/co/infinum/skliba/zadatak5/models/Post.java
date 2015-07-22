package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import co.infinum.skliba.zadatak5.api.PostsDatabase;

/**
 * Created by noxqs on 18.07.15..
 */

@Table(databaseName = PostsDatabase.NAME)
public class Post extends BaseModel implements Serializable{

    @PrimaryKey(autoincrement = true)
    @Column
    public long id;

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
