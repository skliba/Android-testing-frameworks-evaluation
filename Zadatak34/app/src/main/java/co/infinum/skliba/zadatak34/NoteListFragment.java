package co.infinum.skliba.zadatak34;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
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

    private ArrayList<FileInfo> arrList;

    public NoteListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshView(view);
    }

    // izdvojio sam u posebnu metodu jer si dva puta imao isti kod i bio je prilicno velik komad
    private void refreshView(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        RelativeLayout rLayout = (RelativeLayout) view.findViewById(R.id.emptyScreen);
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.listView);

        arrList = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                performFragmentTransaction(ft, EditNoteFragment.newInstance(null));
            }
        });

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

    private void performFragmentTransaction(FragmentTransaction ft, EditNoteFragment editNoteFragment) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            ft.replace(R.id.containter, editNoteFragment, MainActivity.EDIT_FRAGMENT_TAG);
            ft.addToBackStack(null);
        }
        else{
            ft.replace(R.id.landEdit, editNoteFragment, MainActivity.EDIT_FRAGMENT_TAG);
        }
        ft.commit();
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

    public void updateList(){
        refreshView(getActivity().findViewById(R.id.root_view));
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
                EditNoteFragment editNoteFragment = EditNoteFragment.newInstance(arrayList.get((int) fileTitle.getTag()).getFileName() + ".txt");
                performFragmentTransaction(ft, editNoteFragment);
            }
        }

    }
}
