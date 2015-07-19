package co.infinum.skliba.zadatak5.login;

import java.io.Serializable;

/**
 * Created by noxqs on 18.07.15..
 */
public class User implements Serializable{

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
