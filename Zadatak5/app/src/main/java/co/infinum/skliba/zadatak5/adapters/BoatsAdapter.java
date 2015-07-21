package co.infinum.skliba.zadatak5.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.infinum.skliba.zadatak5.activities.DetailsActivity;
import co.infinum.skliba.zadatak5.interfaces.BoatsClickListener;
import co.infinum.skliba.zadatak5.models.Post;
import co.infinum.skliba.zadatak5.R;

/**
 * Created by noxqs on 20.07.15..
 */
public class BoatsAdapter extends RecyclerView.Adapter<BoatsAdapter.ViewHolder> implements BoatsClickListener {

    public static final String BOAT_INFO = "BOAT INFO";
    private ArrayList<Post> arrList;
    private Context context;
    private BoatsClickListener listener;

    public BoatsAdapter(Context context, ArrayList<Post> list) {
        this.arrList = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(arrList.get(position).title);
        Glide.with(context).load(arrList.get(position).imageUrl).into(holder.picture);
        holder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return arrList.size();
    }

    public ArrayList<Post> getArrList() {
        return arrList;
    }

    public Post getArrayListAtIndex(int index) {
        return arrList.get(index);
    }

    @Override
    public void onBoatsClick() {
        Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView desc;
        ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.PostTitle);
            picture = (ImageView) itemView.findViewById(R.id.PostPicture);
        }

    }

}

