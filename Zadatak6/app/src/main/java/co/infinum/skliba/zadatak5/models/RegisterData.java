package co.infinum.skliba.zadatak5.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 22.07.15..
 */
public class RegisterData implements Serializable{

    @SerializedName("user")
    public BodyUserData user;

    public void setUser(BodyUserData user) {
        this.user = user;
    }
}
