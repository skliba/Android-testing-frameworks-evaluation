package co.infinum.skliba.zadatak5.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.infinum.skliba.zadatak5.api.PostsDatabase;

/**
 * Created by noxqs on 18.07.15..
 */
public class BoatsResponse implements Serializable, Parcelable{

    @SerializedName("response")
    private ArrayList<Post> postsList;

    public ArrayList<Post> getRespose() {
        return postsList;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.postsList);
    }

    public BoatsResponse() {

    }

    protected BoatsResponse(Parcel in) {
        this.postsList = new ArrayList<Post>();
        in.readList(this.postsList, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<BoatsResponse> CREATOR = new Parcelable.Creator<BoatsResponse>() {
        public BoatsResponse createFromParcel(Parcel source) {
            return new BoatsResponse(source);
        }

        public BoatsResponse[] newArray(int size) {
            return new BoatsResponse[size];
        }
    };
}
