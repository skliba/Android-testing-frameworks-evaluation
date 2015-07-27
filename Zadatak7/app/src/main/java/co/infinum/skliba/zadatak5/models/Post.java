package co.infinum.skliba.zadatak5.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.ArrayList;

import co.infinum.skliba.zadatak5.api.PostsDatabase;

/**
 * Created by noxqs on 18.07.15..
 */

@Table(databaseName = PostsDatabase.NAME)
public class Post extends BaseModel implements Parcelable, Serializable{

    @PrimaryKey
    @Column
    public long id;
//
//   public ArrayList<UserComment> getCommentArrayList() {
//        return commentArrayList;
//    }

    @Column

    @SerializedName("title")
    public String title;

    @Column
    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("creator")
    public Author creator;
//
//    @SerializedName("comments")
//    public ArrayList<UserComment> commentArrayList;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.imageUrl);
        dest.writeSerializable(this.creator);
       // dest.writeSerializable(this.commentArrayList);
    }

    public Post() {
    }

    protected Post(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.imageUrl = in.readString();
        this.creator = (Author) in.readSerializable();
       // this.commentArrayList = (ArrayList<UserComment>) in.readSerializable();
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
