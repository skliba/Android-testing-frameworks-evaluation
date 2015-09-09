package co.infinum.skliba.BoatIt.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.infinum.skliba.BoatIt.R;
import co.infinum.skliba.BoatIt.models.comments.CommentsResponseBody;


/**
 * Created by noxqs on 26.07.15..
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private ArrayList<CommentsResponseBody> userComments;
    private Context context;

    public CommentsAdapter(ArrayList<CommentsResponseBody> userComments, Context context) {
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
        holder.commentAuthor.setText(userComments.get(position).getAuthor().getFirstName()
                + " "
                + userComments.get(position).author.getLastName());

        setCommentTime(holder, position);

        holder.commentContent.setText(userComments.get(position).getContent());
    }


    private void setCommentTime(CommentsViewHolder holder, int position) {
        if (userComments.get(position).getDaysPassed() != 0)
        {
            if (userComments.get(position).getDaysPassed() == 1)
                holder.commentTime.setText(userComments.get(position).getDaysPassed() + context.getString(R.string.days_singular_text));
            else
                holder.commentTime.setText(userComments.get(position).getDaysPassed() + context.getString(R.string.days_plural_text));
        }
        else if (userComments.get(position).getHoursPassed() != 0)
        {
            if (userComments.get(position).getHoursPassed() == 1)
                holder.commentTime.setText(userComments.get(position).getHoursPassed() + context.getString(R.string.hours_singular_text));
            else
                holder.commentTime.setText(userComments.get(position).getHoursPassed() + context.getString(R.string.hours_plural_text));
        }
        else if (userComments.get(position).getMinutesPassed() != 0)
        {
            if (userComments.get(position).getMinutesPassed() == 1)
                holder.commentTime.setText(userComments.get(position).getMinutesPassed() + context.getString(R.string.minutes_singular_text));
            else
                holder.commentTime.setText(userComments.get(position).getMinutesPassed() + context.getString(R.string.minutes_plural_text));
        }
        else if (userComments.get(position).getSecondsPassed() != 0)
        {
            if (userComments.get(position).getSecondsPassed() == 1)
                holder.commentTime.setText(userComments.get(position).getSecondsPassed() + context.getString(R.string.seconds_singular_text));
            else
                holder.commentTime.setText(userComments.get(position).getSecondsPassed() + context.getString(R.string.seconds_plural_text));
        }

    }

    @Override
    public int getItemCount() {
        return userComments.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        public TextView commentAuthor;
        public TextView commentTime;
        public TextView commentContent;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            commentAuthor = (TextView) itemView.findViewById(R.id.comment_by);
            commentTime = (TextView) itemView.findViewById(R.id.comment_time);
            commentContent = (TextView) itemView.findViewById(R.id.comment_content);
        }
    }
}
