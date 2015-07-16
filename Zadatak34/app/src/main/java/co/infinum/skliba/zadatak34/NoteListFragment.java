package co.infinum.skliba.zadatak34;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import co.infinum.skliba.zadatak34.interfaces.MenuClickHandler;

/**
 * Created by noxqs on 10.07.15..
 */
public class NoteListFragment extends android.support.v4.app.Fragment implements MenuClickHandler {

    public static final String FRAGMENT_NAME = "FRAGMENT NAME";
    public static final String NOTE_TITLE = "NOTE NAME";
    private ArrayList<FileInfo> arrList;

    public NoteListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.note_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        RelativeLayout rLayout = (RelativeLayout) view.findViewById(R.id.emptyScreen);
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.listView);

        arrList = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.containter, new EditNoteFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(FRAGMENT_NAME, "listFragment").commit();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myFiles";
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myFiles");

        if (dir.isDirectory()) {

            if (dir.listFiles().length != 0) {

                rLayout.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                try {

                    File file = new File(path);
                    File files[] = file.listFiles();
                    String holder;
                    for (int i = 0; i < files.length; i++) {
                        holder = files[i].getName();
                        Date dateChanged = new Date(files[i].lastModified());
                        holder = holder.substring(0, holder.lastIndexOf("."));
                        arrList.add(new FileInfo(holder, dateChanged));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                listView.setAdapter(new RecyclerViewAdapter(getActivity().getApplicationContext(), arrList));
            }
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.saveBtn).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.settingsBtn:
                handleSettingsButtonClick();
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void handleSaveButtonClick() {
        //do nothing
    }

    @Override
    public void handleSettingsButtonClick() {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }

    //Adapter

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private ArrayList<FileInfo> arrayList;

        public RecyclerViewAdapter(Context context, ArrayList<FileInfo> arrayList) {
            this.arrayList = arrayList;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.fileTitle.setText(arrayList.get(position).fileName);
            holder.fileInfo.append((" " + String.valueOf(arrayList.get(position).fileDateChanged)));
            holder.fileTitle.setTag(position);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView fileTitle;
            TextView fileInfo;

            public ViewHolder(View view) {
                super(view);
                view.setOnClickListener(this);
                fileTitle = (TextView) view.findViewById(R.id.noteTitle);
                fileInfo = (TextView) view.findViewById(R.id.noteDetails);
            }

            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(NOTE_TITLE, arrayList.get((int) fileTitle.getTag()).getFileName() + ".txt").apply();
                ft.replace(R.id.containter, new EditNoteFragment());
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    clearBackStack();
                    ft.addToBackStack(null);
                }
                else{
                    clearBackStack();
                }

                ft.commit();
            }
        }

    }


    private void clearBackStack() {
        android.support.v4.app.FragmentManager manager = getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void updateList(){
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        RelativeLayout rLayout = (RelativeLayout) getActivity().findViewById(R.id.emptyScreen);
        RecyclerView listView = (RecyclerView) getActivity().findViewById(R.id.listView);

        arrList = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.containter, new EditNoteFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(FRAGMENT_NAME, "listFragment").commit();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myFiles";
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myFiles");

        if (dir.isDirectory()) {

            if (dir.listFiles().length != 0) {

                rLayout.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                try {

                    File file = new File(path);
                    File files[] = file.listFiles();
                    String holder;
                    for (int i = 0; i < files.length; i++) {
                        holder = files[i].getName();
                        Date dateChanged = new Date(files[i].lastModified());
                        holder = holder.substring(0, holder.lastIndexOf("."));
                        arrList.add(new FileInfo(holder, dateChanged));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                listView.setAdapter(new RecyclerViewAdapter(getActivity().getApplicationContext(), arrList));
            }
        }
    }
}
