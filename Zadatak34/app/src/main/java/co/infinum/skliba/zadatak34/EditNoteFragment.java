package co.infinum.skliba.zadatak34;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
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
import co.infinum.skliba.zadatak34.interfaces.OnFileAddedListener;

/**
 * Created by noxqs on 10.07.15..
 */
public class EditNoteFragment extends android.support.v4.app.Fragment implements MenuClickHandler {

    private static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myFiles/";
    private static final String CHANGES_OCCURRED = "CHANGES OCCURRED";
    private static final String FILE_CONTENT = "FILE_CONTENT";
    private boolean changes = false;
    private String fileName;
    private EditText fileTitle;
    private EditText fileContent;
    private String content = "";
    private Menu itemGlobal;
    private OnFileAddedListener mCallback;

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


    public static EditNoteFragment newInstance(String fileName) {
        EditNoteFragment editNoteFragment = new EditNoteFragment();
        Bundle arguments = new Bundle();
        arguments.putString(MainActivity.FILE_NAME, fileName);
        editNoteFragment.setArguments(arguments);
        return editNoteFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnFileAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFileSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(MainActivity.FILE_NAME)) {
            fileName = arguments.getString(MainActivity.FILE_NAME);
        }
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
        setHasOptionsMenu(true);

        fileTitle = (EditText) view.findViewById(R.id.fileTitle);
        fileContent = (EditText) view.findViewById(R.id.fileContent);
        if (savedInstanceState != null && savedInstanceState.containsKey(CHANGES_OCCURRED)) {
            changes = savedInstanceState.getBoolean(CHANGES_OCCURRED);
            if (changes) {
                content = savedInstanceState.getString(FILE_CONTENT);
            }
        }

        if (fileName != null && !fileName.isEmpty()) {
            String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
            fileTitle.setText(fileNameWithoutExtension);
            if (!changes) {
                if (isExternalStorageReadeable()) {
                    String path = DIRECTORY + fileName;

                    try {
                        File file = new File(path);
                        BufferedReader buffer = new BufferedReader(new FileReader(file));

                        String line;

                        while ((line = buffer.readLine()) != null) {
                            content += line + "\n";
                        }

                    } catch (Exception e) {

                    }
                }
            }
            fileContent.setText(content);
        }
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

            mCallback.onFileAdded();
        }
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
        outState.putBoolean(CHANGES_OCCURRED, changes);
        if (changes) {
            outState.putString(FILE_CONTENT, fileContent.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }
}
