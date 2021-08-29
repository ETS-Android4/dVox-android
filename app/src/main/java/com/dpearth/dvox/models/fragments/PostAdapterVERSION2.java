package com.dpearth.dvox.models.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PostAdapterVERSION2 extends RecyclerView.Adapter<PostAdapterVERSION2.PostHolder> {
    private List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_version2, parent, false);
        return new PostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.title.setText(currentPost.getTitle());
        holder.author.setText(currentPost.getAuthor());
        holder.message.setText(currentPost.getMessage());
        holder.hashtag.setText(currentPost.getHashtag());
        holder.commentCount.setText(currentPost.getTitle());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();//We will change the method later
    }

    class PostHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private TextView message;
        private TextView hashtag;
        private TextView commentCount;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.version2_title_text);
            author = itemView.findViewById(R.id.version2_author_text);
            message = itemView.findViewById(R.id.version2_message_text);
            hashtag = itemView.findViewById(R.id.version2_hashtag_text);
            commentCount = itemView.findViewById(R.id.version2_comment_number);
        }
    }
}
