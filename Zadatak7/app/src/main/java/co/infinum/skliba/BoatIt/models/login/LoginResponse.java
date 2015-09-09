package co.infinum.skliba.BoatIt.models.login;

import java.io.Serializable;

/**
 * Created by noxqs on 18.07.15..
 */
public class LoginResponse implements Serializable{

    private LoginUserResponse response;

    public LoginUserResponse getResponse() {
        return response;
    }
}
