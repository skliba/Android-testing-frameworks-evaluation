package co.infinum.skliba.zadatak5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.adapters.CommentsAdapter;
import co.infinum.skliba.zadatak5.models.Post;
import co.infinum.skliba.zadatak5.mvp.view.DetailsView;


public class DetailsActivity extends AppCompatActivity implements DetailsView {

    public static final String BOAT_INFO = "BOAT INFO";
    public static final String POST_DETAILS = "POST DETAILS";

    @Bind(R.id.details_picture)
    ImageView detailsPicture;

    @Bind(R.id.comment_list_view)
    RecyclerView commentListView;

    private CommentsAdapter commentsAdapter;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        commentListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent intent = getIntent();
        post = intent.getParcelableExtra(BOAT_INFO);
        Glide.with(this).load(post.imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(detailsPicture);

        commentListView.setHasFixedSize(true);
        commentsAdapter = new CommentsAdapter(post.commentArrayList, this);
        commentListView.setAdapter(commentsAdapter);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(post.title);

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
    public void showError(@StringRes int error) {

    }
}
