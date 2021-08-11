package com.dpearth.dvox.models.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvMessage;
        private TextView tvHashtag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get all UI (view) elements
            tvTitle = itemView.findViewById(R.id.title_text);
            tvAuthor = itemView.findViewById(R.id.author_text);
            tvMessage = itemView.findViewById(R.id.message_text);
            tvHashtag = itemView.findViewById(R.id.hashtag_text);
        }

        public void bind(Post post) {
            //Assign values to UI elements
            tvTitle.setText(post.getTitle());
            tvAuthor.setText(post.getAuthor());
            tvMessage.setText(post.getMessage());
            tvHashtag .setText(post.getHashtag());
        }
    }
}
