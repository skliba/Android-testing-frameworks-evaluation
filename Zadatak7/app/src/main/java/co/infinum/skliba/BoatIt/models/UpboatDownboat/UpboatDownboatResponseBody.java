package co.infinum.skliba.BoatIt.models.UpboatDownboat;

import com.google.gson.annotations.SerializedName;

/**
 * Created by noxqs on 29.07.15..
 */
public class UpboatDownboatResponseBody {

    @SerializedName("score")
    private long score;

    public long getScore() {
        return score;
    }
}
