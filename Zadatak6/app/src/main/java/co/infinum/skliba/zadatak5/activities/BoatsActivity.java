package co.infinum.skliba.zadatak5.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.SimpleDividerItemDecoration;
import co.infinum.skliba.zadatak5.adapters.BoatsAdapter;
import co.infinum.skliba.zadatak5.api.ApiManager;
import co.infinum.skliba.zadatak5.api.DbFlowPosts;
import co.infinum.skliba.zadatak5.interfaces.BoatsClickListener;
import co.infinum.skliba.zadatak5.models.BoatsResponse;
import co.infinum.skliba.zadatak5.models.Post;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BoatsActivity extends AppCompatActivity implements BoatsClickListener {

    public static final String TOKEN = "TOKEN";
    public static final String ARRAY_LIST = "ARRAY LIST";
    public static final String BOAT_INFO = "BOAT INFO";

    @Bind(R.id.listView)
    RecyclerView postList;

    private String token;
    private BoatsAdapter adapter;
    private DbFlowPosts posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boats);
        ButterKnife.bind(this);

        postList.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        postList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(TOKEN, "");

        posts = new DbFlowPosts();

        if (savedInstanceState != null) {
            adapter = new BoatsAdapter(this, ((ArrayList<Post>) savedInstanceState.getSerializable(ARRAY_LIST)), this);
            postList.setAdapter(adapter);
        } else {
             fetchData();

            }
        }


    private boolean checkIfConnectionExists() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }

    private void fetchData() {
        ApiManager.getService().getAllBoats(token, new Callback<BoatsResponse>() {
            @Override
            public void success(BoatsResponse boatsResponse, Response response) {
                adapter = new BoatsAdapter(BoatsActivity.this, boatsResponse.getRespose(), BoatsActivity.this);
                postList.setAdapter(adapter);
                Log.e("SUCCESS", "BRAVO");

//                posts.dropAllPosts();
//                ArrayList<Post> post = boatsResponse.getRespose();
//                for (int i = 0; i < post.size(); i++) {
//                    posts.addPost(post.get(i));
//                }

            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("FAILED", "" + error.getMessage());
            }
        });
    }

    @Override
    public void onBoatsClick(Post post) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(BOAT_INFO, post);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARRAY_LIST, adapter.getArrList());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_boats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
