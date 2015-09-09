package co.infinum.skliba.BoatIt.models.register;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 22.07.15..
 */
public class RegisterData implements Serializable{

    @SerializedName("user")
    public RegisterDataUser user;

    public void setUser(RegisterDataUser user) {
        this.user = user;
    }
}
