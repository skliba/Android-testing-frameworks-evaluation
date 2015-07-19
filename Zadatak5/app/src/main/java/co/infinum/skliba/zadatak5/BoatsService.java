package co.infinum.skliba.zadatak5;


import co.infinum.skliba.zadatak5.Login.LoginResponse;
import co.infinum.skliba.zadatak5.Login.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by noxqs on 18.07.15..
 */
public interface BoatsService {

    @POST("/api/v1/users/login")
    void login (@Body User user, Callback<LoginResponse> callback);

    @POST("/api/v1/posts{?token}{?page}{?per_page}")
    void getAllBoats(@Path("token") String token,
                     Callback<BoatsResponse> callback);
}
