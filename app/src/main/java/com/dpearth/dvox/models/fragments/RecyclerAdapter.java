package com.dpearth.dvox.models.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;

import org.w3c.dom.Text;

import java.math.BigInteger;
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
        View view = layoutInflater.inflate(R.layout.custome_design, parent, false);
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
        TextView votesView = holder.upvote;
        votesView.setText(post.getHashtag());

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
        public Button upvote;
        public Button downvote;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            drawableRes = R.drawable._01_boar;
            title = (TextView) itemView.findViewById(R.id.post_title);
            author = (TextView) itemView.findViewById(R.id.author_text);
            content = (TextView) itemView.findViewById(R.id.content_post);
            theme = (TextView) itemView.findViewById(R.id.post_theme);
//            upvote;
//            downvote;

        }
    }
}
