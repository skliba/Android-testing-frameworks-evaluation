package co.infinum.skliba.zadatak5.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.models.UserComment;

/**
 * Created by noxqs on 26.07.15..
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private ArrayList<UserComment> userComments;
    private Context context;

    public CommentsAdapter(ArrayList<UserComment> userComments, Context context) {
        this.userComments = userComments;
        this.context = context;
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.comment_list_item, parent, false);
        return new CommentsViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        holder.commentAuthor.setText(userComments.get(position).author.firstName + " " + userComments.get(position).author.lastName);
        holder.commentContent.setText(userComments.get(position).content);
    }

    @Override
    public int getItemCount() {
        return userComments.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        public TextView commentAuthor;
        public TextView commentContent;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            commentAuthor = (TextView) itemView.findViewById(R.id.comment_by);
            commentContent = (TextView) itemView.findViewById(R.id.comment_content);
        }
    }
}
