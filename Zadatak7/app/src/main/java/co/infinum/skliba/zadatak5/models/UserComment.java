package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;

import java.io.Serializable;

/**
 * Created by noxqs on 26.07.15..
 */
public class UserComment implements Serializable{

    @SerializedName("id")
    public long id;

    @SerializedName("content")
    public String content;

    @SerializedName("author")
    public CommentAuthor author;
}
