package co.infinum.skliba.BoatIt.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.skliba.BoatIt.R;
import co.infinum.skliba.BoatIt.adapters.BoatsAdapter;
import co.infinum.skliba.BoatIt.api.DbFlowPosts;
import co.infinum.skliba.BoatIt.helpers.MvpFactory;
import co.infinum.skliba.BoatIt.interfaces.BoatsClickListener;
import co.infinum.skliba.BoatIt.interfaces.ConnectivityChecker;
import co.infinum.skliba.BoatIt.models.boats.Post;
import co.infinum.skliba.BoatIt.mvp.presenter.BoatsPresenter;
import co.infinum.skliba.BoatIt.mvp.view.BoatsView;


public class BoatsActivity extends AppCompatActivity implements BoatsClickListener, BoatsView, ConnectivityChecker {

    public static final String TOKEN = "TOKEN";
    public static final String ARRAY_LIST = "ARRAY LIST";
    public static final String BOAT_INFO = "BOAT INFO";

    @Bind(R.id.list_view)
    RecyclerView postList;

    private String token;
    private BoatsAdapter adapter;
    private DbFlowPosts posts;
    private BoatsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boats);
        ButterKnife.bind(this);

        postList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(TOKEN, "");

        posts = new DbFlowPosts();

        presenter = MvpFactory.getPresenter(this);

        if (savedInstanceState != null) {
            adapter = new BoatsAdapter(this, ((ArrayList<Post>) savedInstanceState.getSerializable(ARRAY_LIST)), this);
            postList.setAdapter(adapter);
        } else {
            if (checkIfConnectionExists()) {
                posts.dropAllPosts();
                presenter.onFetchBoats(token);
            } else {
                adapter = new BoatsAdapter(this, (ArrayList<Post>) posts.getPosts(), this);
                postList.setAdapter(adapter);
            }
        }
    }

    @Override
    public boolean checkIfConnectionExists() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }

    @Override
    public void onBoatsClick(Post post) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(BOAT_INFO, (Parcelable) post);
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

    @Override
    public void onBoatsRecieved(ArrayList<Post> arrayList) {
        posts.dropAllPosts();
        adapter = new BoatsAdapter(BoatsActivity.this, arrayList, BoatsActivity.this);
        postList.setAdapter(adapter);
        Log.e("SUCCESS", "BRAVO");

        for (int i = 0; i < arrayList.size(); i++) {
            posts.addPost(arrayList.get(i));
        }
    }

    @Override
    public void onTokenExpired() {

    }

    @Override
    public void showError(@StringRes int error) {

    }


}
