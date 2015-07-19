package co.infinum.skliba.zadatak5;

import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BoatsActivity extends ActionBarActivity {

    public static final String TOKEN = "TOKEN";
    private String token;
    private BoatsAdapter adapter;
    private RecyclerView postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boats);

        postList = (RecyclerView) findViewById(R.id.listView);
        postList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(TOKEN, "");

        ApiManager.getService().getAllBoats(token, new Callback<BoatsResponse>() {
            @Override
            public void success(BoatsResponse boatsResponse, Response response) {
                adapter = new BoatsAdapter(BoatsActivity.this, boatsResponse.getRespose());
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
