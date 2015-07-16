package co.infinum.skliba.zadatak34;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import co.infinum.skliba.zadatak34.interfaces.MenuClickHandler;


public class MainActivity extends ActionBarActivity implements MenuClickHandler{

    public static final String LIST_FRAGMENT_TAG = "LIST_FRAGMENT_TAG";
    public static final String EDIT_FRAGMENT_TAG = "EDIT_FRAGMENT_TAG";
    public static final String FILE_NAME = "FILE_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileName = null;
        if (savedInstanceState != null && savedInstanceState.containsKey(FILE_NAME)) {
            fileName = savedInstanceState.getString(FILE_NAME);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.containter, new NoteListFragment(), LIST_FRAGMENT_TAG);
            ft.commit();
            // ako smo radili na nekom doumentu mijenjamo list fragment s edit fragmentom i stavljamo list na back stack
            // inace normalno prikazujemo list fragment jer nema smisla pokazati prazan dokument
            if (fileName != null) {
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.containter, EditNoteFragment.newInstance(fileName), EDIT_FRAGMENT_TAG);
                ft.addToBackStack(null);
                ft.commit();
            }

        } else {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.landList, new NoteListFragment(), LIST_FRAGMENT_TAG);
            ft.commit();
            ft.replace(R.id.landEdit, EditNoteFragment.newInstance(fileName), EDIT_FRAGMENT_TAG);
            ft = getSupportFragmentManager().beginTransaction();
            ft.commit();
        }
    }

    private void clearBackStack() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
        EditNoteFragment editNoteFragment = (EditNoteFragment) getSupportFragmentManager().findFragmentByTag(EDIT_FRAGMENT_TAG);
        if (editNoteFragment != null) {
            outState.putString(FILE_NAME, editNoteFragment.getFileName());
        }
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
}
