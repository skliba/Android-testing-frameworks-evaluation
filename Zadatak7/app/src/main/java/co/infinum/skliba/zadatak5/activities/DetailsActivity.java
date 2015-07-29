package co.infinum.skliba.zadatak5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.adapters.CommentsAdapter;
import co.infinum.skliba.zadatak5.helpers.MvpFactory;
import co.infinum.skliba.zadatak5.models.boats.Post;
import co.infinum.skliba.zadatak5.models.comments.CommentsResponseBody;
import co.infinum.skliba.zadatak5.mvp.presenter.DetailsPresenter;
import co.infinum.skliba.zadatak5.mvp.view.DetailsView;


public class DetailsActivity extends AppCompatActivity implements DetailsView {

    public static final String BOAT_INFO = "BOAT INFO";
    public static final String POST_DETAILS = "POST DETAILS";

    @Bind(R.id.details_picture)
    ImageView detailsPicture;

    @Bind(R.id.comment_list_view)
    RecyclerView commentListView;

    @Bind(R.id.button_upboat)
    Button buttonUpboat;

    @Bind(R.id.button_downboat)
    Button buttonDownboat;

    private Post post;
    private String token;
    private static int cntUpboat = 0;
    private static int cntDownboat = 0;

    private DetailsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        cntUpboat = 0;
        cntDownboat = 0;

        commentListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent intent = getIntent();
        post = intent.getParcelableExtra(BOAT_INFO);
        Glide.with(this).load(post.imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(detailsPicture);

        token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("TOKEN", "");

        presenter = MvpFactory.getPresenter(this);
        presenter.getCommentsPerPost(post, token);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(post.title);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter = MvpFactory.getPresenter(this);
        presenter.getCommentsPerPost(post, token);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(POST_DETAILS, post);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_new_post) {
            Intent intent = new Intent(DetailsActivity.this, CommentActivity.class);
            intent.putExtra(BOAT_INFO, (Parcelable) post);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetailsRecieved(Post post) {

    }

    @Override
    public void onTokenExpired() {

    }

    @Override
    public void onCommentsRecieved(ArrayList<CommentsResponseBody> response) {
        CommentsAdapter commentsAdapter = new CommentsAdapter(response, this);
        commentListView.setAdapter(commentsAdapter);
    }

    @Override
    public void showError(@StringRes int error) {
        Toast.makeText(getApplicationContext(), getString(R.string.PostDetailsError), Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_downboat)
    void onClickDownboat(){
        presenter.onDownboatClicked(post);
    }

    @Override
    public void onDownboatSuccess() {
        if(cntDownboat == 0){
            Toast.makeText(getApplicationContext(), "Successful downboat", Toast.LENGTH_SHORT).show();
            cntDownboat++;
            cntUpboat = 0;
        }
        else{
            Toast.makeText(getApplicationContext(), "You can press downboat button only once" , Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.button_upboat)
    void onClickUpboat(){
        presenter.onUpboatClicked(post);
    }

    @Override
    public void onUpboatSuccess() {
        if(cntUpboat == 0){
            Toast.makeText(getApplicationContext(), "Successful upboat", Toast.LENGTH_SHORT).show();
            cntUpboat++;
            cntDownboat = 0;
        }
        else{
            Toast.makeText(getApplicationContext(), "You can press upboat button only once" , Toast.LENGTH_SHORT).show();
        }

    }


}
