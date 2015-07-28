package co.infinum.skliba.zadatak5.models.boats;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.List;

import co.infinum.skliba.zadatak5.api.PostsDatabase;
import co.infinum.skliba.zadatak5.models.comments.CommentsResponseBody;

/**
 * Created by noxqs on 18.07.15..
 */

@Table(databaseName = PostsDatabase.NAME)
public class Post extends BaseModel implements Parcelable, Serializable {

    @PrimaryKey
    @Column
    public long id;

    @Column
    @SerializedName("title")
    public String title;

    @Column
    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("comments")
    private List<CommentsResponseBody> list;

    public int getListCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    public void setTitle(String title) {
        this.title = title;
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
    }

    public Post() {
    }

    protected Post(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.imageUrl = in.readString();
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
