package co.infinum.skliba.BoatIt.models.comments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by noxqs on 27.07.15..
 */
public class CommentsResponse implements Serializable{

    @SerializedName("response")
    public ArrayList<CommentsResponseBody> response;

    @SerializedName("count")
    public long count;

    public ArrayList<CommentsResponseBody> getResponse() {
        return response;
    }

    public long getCount() {
        return count;
    }
}
