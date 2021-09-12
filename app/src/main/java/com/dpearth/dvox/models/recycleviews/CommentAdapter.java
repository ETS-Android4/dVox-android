package com.dpearth.dvox.models.recycleviews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dpearth.dvox.CommentActivity;
import com.dpearth.dvox.R;
import com.dpearth.dvox.livedata.Votes;
import com.dpearth.dvox.smartcontract.Comment;
import com.dpearth.dvox.smartcontract.Post;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

//Todo: Go to comment view when post card is pressed
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>   {

    private List<Comment> comments;

    public boolean shimmer = true;

    public CommentAdapter(List<Comment> comments){
        this.comments = Collections.unmodifiableList(comments);
    }

    @Override
    public int getItemViewType(int position) {
        if (comments.get(position).getCommentAuthor().equals("███████"))
            return 1;
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        View shimmerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_shimmer, parent, false);

        switch(viewType){
            case 0:
                return new ViewHolder(view);
            case 1:
                return new ViewHolder(shimmerView);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setPosts(List<Post> posts) {
        this.comments = comments;
        notifyDataSetChanged();//We will change the method later
    }

    public Comment getCommentsAt(int position){
        return comments.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAuthor;
        private TextView tvMessage;
        private ImageView tvAvatar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get all UI (view) elements
            tvAuthor = itemView.findViewById(R.id.comment_author);
            tvMessage = itemView.findViewById(R.id.comment_message);
            tvAvatar = itemView.findViewById(R.id.comment_post_avatar);
        }

        public void bind(Comment comment) {
            //Assign values to UI elements
            tvAuthor.setText(comment.getCommentAuthor());
            tvMessage.setText(comment.getCommentMessage());

            String uri = "drawable/" + stringToAvatar(comment.getCommentAuthor()).toLowerCase();

            if (uri.equals("drawable/hacker") && comment.getCommentAuthor().equals("███████"))
                uri = "drawable/black_square";

            int imageResource = itemView.getContext().getResources().getIdentifier(uri, null, itemView.getContext().getPackageName());

            tvAvatar.setImageResource(imageResource);
        }

        public String stringToAvatar(String username){
            String[] array = username.split("_");

            if (array.length == 3){
                return array[1];
            } else if (array.length == 4){
                return array[1] + "_" + array[2];
            } else {
                return "Hacker";
            }
        }

    }
}
