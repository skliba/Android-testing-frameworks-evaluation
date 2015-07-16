package co.infinum.skliba.zadatak34;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;

import co.infinum.skliba.zadatak34.interfaces.MenuClickHandler;

/**
 * Created by noxqs on 16.07.15..
 */
public class SettingsFragment extends Fragment implements MenuClickHandler {

    public static final String SELECTED_LANGUAGE = "SELECTED LANGUAGE";
    public static final String SELECTED_COLOR = "SELECTED COLOR";
    public static final String SELECTED_LANGUAGE_POSITION = "SELECTED LANGUAGE POSITION";
    public static final String SELECTED_COLOR_POSITION = "SELECTED COLOR POSITION";
    private Spinner languageListSpinner;
    private Spinner colorListSpinner;
    private String selectedLanguage, selectedColor;
    private int selectedLanguagePos, selectedColorPos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_layout, container, false);
        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        languageListSpinner = (Spinner) view.findViewById(R.id.langList);
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.language_list, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageListSpinner.setAdapter(langAdapter);

        colorListSpinner = (Spinner) view.findViewById(R.id.colorList);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.color_list, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorListSpinner.setAdapter(colorAdapter);

        languageListSpinner.setOnItemSelectedListener(new ItemSelectedListener());
        colorListSpinner.setOnItemSelectedListener(new ItemSelectedListener());

        updateSelectedItems();

    }

    private void updateSelectedItems() {

        int colorPos = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getInt(SELECTED_COLOR_POSITION, -1);
        int pos = 0;
        if (colorPos != -1) {
            if (colorPos == 0) {
                pos = 0;
            } else if (colorPos == 1) {
                pos = 1;
            } else if (colorPos == 2) {
                pos = 2;
            }
        }
        colorListSpinner.setSelection(pos);

        int lang = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getInt(SELECTED_LANGUAGE_POSITION, -1);
        if (lang != -1) {
            if (lang == 0) {
                pos = 0;
            } else if (lang == 1) {
                pos = 1;
            } else if (lang == 2) {
                pos = 2;
            }
        }
        languageListSpinner.setSelection(pos);

    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedLanguagePos = languageListSpinner.getSelectedItemPosition();
            selectedLanguage = languageListSpinner.getSelectedItem().toString();
            selectedColorPos = colorListSpinner.getSelectedItemPosition();
            selectedColor = colorListSpinner.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.settingsBtn).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menu.findItem(R.id.saveBtn).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.saveBtn) {
            handleSaveButtonClick();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleSaveButtonClick() {
        PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putInt(SELECTED_LANGUAGE_POSITION, selectedLanguagePos).apply();
        PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putInt(SELECTED_COLOR_POSITION, selectedColorPos).apply();
        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleSettingsButtonClick() {

    }
}
