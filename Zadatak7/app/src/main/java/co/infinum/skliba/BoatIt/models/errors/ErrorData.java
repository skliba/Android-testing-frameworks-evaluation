package co.infinum.skliba.BoatIt.models.errors;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by noxqs on 26.07.15..
 */
public class ErrorData implements Serializable {

    @SerializedName("email")
    public ArrayList<String> errorEmail;

    @SerializedName("password")
    public ArrayList<String> errorPassword;

    public ArrayList<String> getErrorEmail() {
        return errorEmail;
    }

    public ArrayList<String> getErrorPassword() {
        return errorPassword;
    }

}
