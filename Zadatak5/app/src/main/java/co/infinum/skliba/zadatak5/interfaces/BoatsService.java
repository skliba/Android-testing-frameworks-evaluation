package co.infinum.skliba.zadatak5.interfaces;


import co.infinum.skliba.zadatak5.models.BoatsResponse;
import co.infinum.skliba.zadatak5.login.LoginResponse;
import co.infinum.skliba.zadatak5.login.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by noxqs on 18.07.15..
 */
public interface BoatsService {

    @POST("/api/v1/users/login")
    void login (@Body User user, Callback<LoginResponse> callback);

    @GET("/api/v1/posts")
    void getAllBoats(@Query("token") String token,
                     Callback<BoatsResponse> callback);
}
