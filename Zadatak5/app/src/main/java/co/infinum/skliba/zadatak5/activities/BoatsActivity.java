package co.infinum.skliba.zadatak5.activities;

import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import co.infinum.skliba.zadatak5.api.ApiManager;
import co.infinum.skliba.zadatak5.models.BoatsResponse;
import co.infinum.skliba.zadatak5.models.Post;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.SimpleDividerItemDecoration;
import co.infinum.skliba.zadatak5.adapters.BoatsAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BoatsActivity extends AppCompatActivity {

    public static final String TOKEN = "TOKEN";
    public static final String ARRAY_LIST = "ARRAY LIST";

    private String token;
    private BoatsAdapter adapter;
    private RecyclerView postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boats);

        postList = (RecyclerView) findViewById(R.id.listView);
        postList.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        postList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("My Posts");

        token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(TOKEN, "");

        if (savedInstanceState != null) {
            adapter = new BoatsAdapter(this, ((ArrayList<Post>) savedInstanceState.getSerializable(ARRAY_LIST)));
            postList.setAdapter(adapter);
            //kaos, kaos, sta drugo da ti covjek kaze
        } else {
            fetchData();
        }
    }

    private void fetchData() {
        ApiManager.getService().getAllBoats(token, new Callback<BoatsResponse>() {
            @Override
            public void success(BoatsResponse boatsResponse, Response response) {
                adapter = new BoatsAdapter(BoatsActivity.this, boatsResponse.getRespose());
                //Pa sta je ovo
                postList.setAdapter(adapter);
                Log.e("SUCCESS", "BRAVO");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("FAILED", error.toString());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARRAY_LIST, adapter.getArrList());
        //Ovo ti ne valja smeće
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
        //Koja katastrofa, ja ne vjerujem

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
