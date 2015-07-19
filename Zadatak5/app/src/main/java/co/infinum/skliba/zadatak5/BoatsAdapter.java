package co.infinum.skliba.zadatak5;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BoatsAdapter extends RecyclerView.Adapter<BoatsAdapter.ViewHolder>{

    private ArrayList<Post> arrList;
    private Context context;

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
    }

    @Override
    public int getItemCount() {
        return arrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView desc;
        ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.PostTitle);
            picture = (ImageView) itemView.findViewById(R.id.PostPicture);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

