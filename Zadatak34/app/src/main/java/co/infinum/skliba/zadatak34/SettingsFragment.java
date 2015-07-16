package co.infinum.skliba.zadatak34;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by noxqs on 16.07.15..
 */
public class SettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_layout, container, false);
        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Spinner languageListSpinner = (Spinner) view.findViewById(R.id.langList);
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.language_list, android.R.layout.simple_spinner_item);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageListSpinner.setAdapter(langAdapter);

        Spinner colorListSpinner = (Spinner) view.findViewById(R.id.colorList);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.color_list, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorListSpinner.setAdapter(colorAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
