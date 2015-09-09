package co.infinum.skliba.BoatIt.models.UpboatDownboat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 29.07.15..
 */
public class UpboatDownboatResponse implements Serializable {

    @SerializedName("response")
    private UpboatDownboatResponseBody response;

    public UpboatDownboatResponseBody getResponse() {
        return response;
    }
}
