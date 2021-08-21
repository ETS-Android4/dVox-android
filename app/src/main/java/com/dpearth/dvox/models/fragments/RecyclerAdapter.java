package com.dpearth.dvox.models.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public RecyclerAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    /** Create new view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /** Bind/Insert Data
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Post post = posts.get(position);

        // Set item views based on your views and data model
        TextView titleView = holder.title;
        titleView.setText(post.getTitle());

        TextView authorView = holder.author;
        authorView.setText(post.getAuthor());

        TextView contentView = holder.content;
        contentView.setText(post.getMessage());

        TextView hashtagView = holder.theme;
        hashtagView.setText(post.getHashtag());

        //Only Upvotes for now
        TextView upvoteNumberView = holder.upvote_number;
        upvoteNumberView.setText(post.getUpVotes().toString());

        TextView downvoteNumberView = holder.downvote_number;
        downvoteNumberView.setText(post.getDownVotes().toString());

        TextView commentNumberView = holder.comment_number;
        commentNumberView.setText(post.getCommentCount().toString());

    }

    /** Count -> Creates # of views
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public int drawableRes;
        public TextView title;
        public TextView author;
        public TextView content;
        public TextView theme;
        public ImageView upvote;
        public ImageView downvote;
        public ImageView comment;
        public TextView upvote_number;
        public TextView downvote_number;
        public TextView comment_number;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            drawableRes = R.drawable.boar;
            title = (TextView) itemView.findViewById(R.id.post_title);
            author = (TextView) itemView.findViewById(R.id.author_text);
            content = (TextView) itemView.findViewById(R.id.content_post);
            theme = (TextView) itemView.findViewById(R.id.post_theme);
            upvote = (ImageView) itemView.findViewById(R.id.upvote_button);
            downvote = (ImageView) itemView.findViewById(R.id.downvote_button);
            comment = (ImageView) itemView.findViewById(R.id.comment_button);
            upvote_number = (TextView) itemView.findViewById(R.id.upvote_number);
            downvote_number = (TextView) itemView.findViewById(R.id.downvote_number);
            comment_number = (TextView) itemView.findViewById(R.id.comment_number);
        }
    }
}
