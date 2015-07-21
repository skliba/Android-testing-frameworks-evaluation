package co.infinum.skliba.zadatak5.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import co.infinum.skliba.zadatak5.models.Post;
import co.infinum.skliba.zadatak5.R;


public class DetailsActivity extends ActionBarActivity {

    public static final String BOAT_INFO = "BOAT INFO";
    public static final String POST_DETAILS = "POST DETAILS";
    private Post post;
    private ImageView detailsPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        post = ((Post) intent.getSerializableExtra(BOAT_INFO));
        detailsPicture = (ImageView) findViewById(R.id.DetailsPicture);
        Glide.with(this).load(post.imageUrl).into(detailsPicture);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(post.title);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(POST_DETAILS, post);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
