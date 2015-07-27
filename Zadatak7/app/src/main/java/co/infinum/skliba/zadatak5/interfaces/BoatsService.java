package co.infinum.skliba.zadatak5.interfaces;


import com.squareup.okhttp.Call;

import co.infinum.skliba.zadatak5.models.BoatsResponse;
import co.infinum.skliba.zadatak5.models.CommentBody;
import co.infinum.skliba.zadatak5.models.CommentBodyContent;
import co.infinum.skliba.zadatak5.models.CreateCommentResponse;
import co.infinum.skliba.zadatak5.models.LoginResponse;
import co.infinum.skliba.zadatak5.models.RegisterDataUser;
import co.infinum.skliba.zadatak5.models.RegisterData;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by noxqs on 18.07.15..
 */
public interface BoatsService {

    @POST("/api/v1/users/login")
    void login (@Body RegisterDataUser user, Callback<LoginResponse> callback);

    @GET("/api/v1/posts")
    void getAllBoats(@Query("token") String token,
                     Callback<BoatsResponse> callback);

    @POST("/api/v1/users/register")
    void register(@Body RegisterData user, Callback<LoginResponse> callback);

    @POST("/api/v1/posts/{post_id}/comments")
    void createAComment(@Path("post_id") String postId, @Query("token") String token, @Body CommentBody commentBody, Callback<CreateCommentResponse> callBack);
}
