package co.infinum.skliba.zadatak34;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import co.infinum.skliba.zadatak34.interfaces.MenuClickHandler;
import co.infinum.skliba.zadatak34.interfaces.OnFileAddedListener;
import co.infinum.skliba.zadatak34.interfaces.OnFileSelectedListener;


public class MainActivity extends ActionBarActivity implements MenuClickHandler, OnFileAddedListener, OnFileSelectedListener {

    public static final String FILE_NAME = "FILE_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.containter, new NoteListFragment());
                ft.commit();
            }
        } else {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.containter);
            if (currentFragment != null) {
                if (currentFragment instanceof NoteListFragment) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.remove(currentFragment);
                    ft.commit();
                }
            } else {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.containter, new NoteListFragment());
                ft.commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.saveBtn).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settingsBtn) {
            handleSettingsButtonClick();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void handleSaveButtonClick() {

    }

    @Override
    public void handleSettingsButtonClick() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFileAdded() {
        NoteListFragment noteListFragment = (NoteListFragment) getSupportFragmentManager().findFragmentById(R.id.note_list_fragment);
        if (noteListFragment != null) {
            noteListFragment.updateList();
        }
    }

    @Override
    public void onFileSelected(String fileName) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containter, EditNoteFragment.newInstance(fileName));
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        } else {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.containter);
            if (currentFragment != null && currentFragment instanceof EditNoteFragment) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.containter, new NoteListFragment());
                ft.commit();
            } else {
                super.onBackPressed();
            }
        }
    }
}
