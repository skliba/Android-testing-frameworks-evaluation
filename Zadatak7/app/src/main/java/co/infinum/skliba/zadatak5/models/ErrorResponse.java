package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 26.07.15..
 */
public class ErrorResponse implements Serializable {

    @SerializedName("error")
    public String error;

    @SerializedName("error_description")
    public String errorDesc;

    @SerializedName("messages")
    public ErrorData errorMsg;

    public ErrorData getErrorMsg() {
        return errorMsg;
    }

}
