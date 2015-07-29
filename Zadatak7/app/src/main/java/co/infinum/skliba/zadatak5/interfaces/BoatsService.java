package co.infinum.skliba.zadatak5.interfaces;


import co.infinum.skliba.zadatak5.models.UpboatDownboat.UpboatDownboatResponse;
import co.infinum.skliba.zadatak5.models.boats.BoatsResponse;
import co.infinum.skliba.zadatak5.models.comments.CommentsResponse;
import co.infinum.skliba.zadatak5.models.comments.CreateCommentBody;
import co.infinum.skliba.zadatak5.models.comments.CreateCommentResponse;
import co.infinum.skliba.zadatak5.models.login.LoginBody;
import co.infinum.skliba.zadatak5.models.login.LoginResponse;
import co.infinum.skliba.zadatak5.models.register.RegisterData;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by noxqs on 18.07.15..
 */
public interface BoatsService {

    @POST("/api/v1/users/login")
    void login (@Body LoginBody user,
                Callback<LoginResponse> callback);

    @GET("/api/v1/posts")
    void getAllBoats(@Query("token") String token,
                     Callback<BoatsResponse> callback);

    @POST("/api/v1/users/register")
    void register(@Body RegisterData user,
                  Callback<LoginResponse> callback);

    @POST("/api/v1/posts/{post_id}/comments")
    void createAComment(@Path("post_id") String postId,
                        @Query("token") String token,
                        @Body CreateCommentBody commentBody,
                        Callback<CreateCommentResponse> callBack);

    @GET("/api/v1/posts/{post_id}/comments")
    void getAllComments(@Path("post_id") String postId,
                        @Query("token") String token,
                        Callback<CommentsResponse> callback);


    @GET("/api/v1/posts/{id}/upboat")
    void upboat(@Path("id") String id,
                @Query("token") String token,
                Callback<UpboatDownboatResponse> callback);

    @GET("/api/v1/posts/{id}/downboat")
    void downboat(@Path("id") String id,
                  @Query("token") String token,
                  Callback<UpboatDownboatResponse> callback);
}
