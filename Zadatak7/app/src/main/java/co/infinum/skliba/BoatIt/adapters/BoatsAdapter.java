package co.infinum.skliba.BoatIt.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import co.infinum.skliba.BoatIt.interfaces.BoatsClickListener;
import co.infinum.skliba.BoatIt.models.boats.Post;
import co.infinum.skliba.BoatIt.R;

/**
 * Created by noxqs on 20.07.15..
 */
public class BoatsAdapter extends RecyclerView.Adapter<BoatsAdapter.ViewHolder> {


    private ArrayList<Post> arrList;
    private Context context;
    private BoatsClickListener listener;

    public BoatsAdapter(Context context, ArrayList<Post> list, BoatsClickListener listener) {
        this.arrList = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(arrList.get(position).title);
        holder.subtitle.setText(context.getString(R.string.comments_num, arrList.get(position).getListCount()));
        Glide.with(context).load(arrList.get(position).imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.picture);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBoatsClick(getArrayListAtIndex(position));
            }
        });
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView subtitle;
        ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.post_title);
            subtitle = (TextView) itemView.findViewById(R.id.number_of_comments);
            picture = (ImageView) itemView.findViewById(R.id.post_picture);
        }

    }

}

