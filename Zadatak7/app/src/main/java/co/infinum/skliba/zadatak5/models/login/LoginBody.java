package co.infinum.skliba.zadatak5.models.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 28.07.15..
 */
public class LoginBody implements Serializable{

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
