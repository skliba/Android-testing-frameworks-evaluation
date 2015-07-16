package co.infinum.skliba.zadatak34;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import co.infinum.skliba.zadatak34.interfaces.MenuClickHandler;

/**
 * Created by noxqs on 10.07.15..
 */
public class EditNoteFragment extends android.support.v4.app.Fragment implements MenuClickHandler {

    public static final String FRAGMENT_NAME = "FRAGMENT NAME";
    public static final String NOTE_TITLE = "NOTE NAME";
    public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myFiles/";
    public static final String CHANGES_OCCURRED = "CHANGES OCCURRED";
    private boolean changes = false;
    private EditText fileTitle;
    private EditText fileContent;
    private String content = "";
    private Menu itemGlobal;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            itemGlobal.findItem(R.id.saveBtn).setEnabled(true);
            changes = true;
        }
    };


    public EditNoteFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edit_note, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(FRAGMENT_NAME, "editFragment").commit();
        setHasOptionsMenu(true);


        fileTitle = (EditText) view.findViewById(R.id.fileTitle);
        fileContent = (EditText) view.findViewById(R.id.fileContent);
        if (savedInstanceState != null) {
            changes = savedInstanceState.getBoolean(CHANGES_OCCURRED);
        }

        String fileNamewithExtension = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(NOTE_TITLE, "");

        if (!fileNamewithExtension.isEmpty()) {
            String fileNameWithoutExtension = fileNamewithExtension.substring(0, fileNamewithExtension.lastIndexOf("."));
            fileTitle.setText(fileNameWithoutExtension);
            if (isExternalStorageReadeable()) {
                String path = DIRECTORY + fileNamewithExtension;

                try {
                    File file = new File(path);
                    BufferedReader buffer = new BufferedReader(new FileReader(file));

                    String line;

                    while ((line = buffer.readLine()) != null) {
                        content += line + "\n";
                    }

                    fileContent.setText(content);
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().clear().commit();

                } catch (Exception e) {

                }
            }
        }
    }

    public void setFileContent(String fileContent) {
        this.fileContent.setText(fileContent);
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle.setText(fileTitle);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.saveBtn).setEnabled(false);
        this.itemGlobal = menu;
        fileContent.addTextChangedListener(textWatcher);
        fileTitle.addTextChangedListener(textWatcher);
        if (changes) {
            menu.findItem(R.id.saveBtn).setEnabled(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.saveBtn).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.saveBtn:
                handleSaveButtonClick();
                break;
            case R.id.settingsBtn:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialog() {
        AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
        ad.setCancelable(false);
        ad.setTitle("something");
        ad.setMessage("somethingmessage");
        ad.setButton(getActivity().getString(R.string.settingsMenu), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }

    @Override
    public void handleSaveButtonClick() {
        if (isExternalStorageWritable()) {
            File file = Environment.getExternalStorageDirectory();
            File directory = new File(file.getAbsolutePath() + "/myFiles");

            //check if directory exists, if not create one
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String newFileNameWithoutExtension = fileTitle.getText().toString();
            String newFileNameWithExtension = newFileNameWithoutExtension.concat(".txt");

            try {
                //create a testfile from which we determine whether file already exists or not, in the directory
                File testFile = new File(DIRECTORY + newFileNameWithExtension);
                //if it doesnt exist, create a new one and write something in it
                if (!testFile.exists()) {
                    File myDoc = new File(directory, fileTitle.getText().toString() + ".txt");
                    FileOutputStream outputStream = new FileOutputStream(myDoc);
                    outputStream.write(fileContent.getText().toString().getBytes());
                    outputStream.close();
                    Toast.makeText(getActivity().getApplicationContext(), "File saved", Toast.LENGTH_SHORT).show();
                    itemGlobal.findItem(R.id.saveBtn).setEnabled(false);
                    changes = false;
                }
                //if it does, just write, dont create a new file
                else {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(testFile));
                    bufferedWriter.write(fileContent.getText().toString());
                    bufferedWriter.close();
                    Toast.makeText(getActivity().getApplicationContext(), "File saved", Toast.LENGTH_SHORT).show();
                    itemGlobal.findItem(R.id.saveBtn).setEnabled(false);
                    changes = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                refreshList();
            }
        }
    }

    private void refreshList() {
        NoteListFragment fragment = (NoteListFragment) getActivity().getSupportFragmentManager().findFragmentByTag("listFragment");
        fragment.updateList();
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private boolean isExternalStorageReadeable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    @Override
    public void handleSettingsButtonClick() {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CHANGES_OCCURRED, changes);
    }
}
