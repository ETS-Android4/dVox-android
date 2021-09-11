package com.dpearth.dvox.models.recycleviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.dpearth.dvox.smartcontract.Post;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

//Todo: Go to comment view when post card is pressed
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>   {

    private Fragment fragment;
    private Context context;
    private List<Post> posts;

    public boolean shimmer = true;

    public PostAdapter(Fragment fragment, List<Post> posts){
        this.fragment = fragment;
        this.context = fragment.getContext();
        this.posts = Collections.unmodifiableList(posts);
    }

    @Override
    public int getItemViewType(int position) {
        if (posts.get(position).getAuthor().equals("███████"))
            return 1;
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        View shimmerView = LayoutInflater.from(context).inflate(R.layout.item_post_shimmer, parent, false);

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
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();//We will change the method later
    }

    public Post getPostAt(int position){
        return posts.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvMessage;
        private TextView tvHashtag;
        private TextView tvCommentNumber;
        private ImageView tvAvatar;
        private ImageView commentButton;
        private ImageView upvoteButton;
        private ImageView downvoteButton;
        private TextView upvotes;
        private TextView downvotes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get all UI (view) elements
            tvTitle = itemView.findViewById(R.id.acPostTitle);
            tvAuthor = itemView.findViewById(R.id.acPostAuthor);
            tvMessage = itemView.findViewById(R.id.acPostMessage);
            tvHashtag = itemView.findViewById(R.id.acPostHashtag);
            tvCommentNumber = itemView.findViewById(R.id.acPostComments);
            tvAvatar = itemView.findViewById(R.id.avatar_image);
            commentButton = itemView.findViewById(R.id.acCommentButton);
            upvoteButton = itemView.findViewById(R.id.upvote_button);
            downvoteButton = itemView.findViewById(R.id.downvote_button);
            upvotes = itemView.findViewById(R.id.acPostUpvotes);
            downvotes = itemView.findViewById(R.id.acPostDownvotes);
        }

        public void bind(Post post) {
            //Assign values to UI elements
            tvTitle.setText(post.getTitle());
            tvAuthor.setText(post.getAuthor());
            tvMessage.setText(post.getMessage());
            tvHashtag.setText(post.getHashtag());
            tvCommentNumber.setText(String.valueOf(post.getCommentCount()));

            commentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Get new activity
                    Intent intent = new Intent(fragment.getActivity(), CommentActivity.class);

                    //Create bundle to pass the object to the activity
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Post", (Serializable) post);
                    intent.putExtras(bundle);

                    //Start activity
                    fragment.startActivity(intent);
                }
            });


            Votes votes = new Votes(3);
            votes.getVotesFireStore(true);
            upvotes.setText(String.valueOf(votes.getUpvotes()));

//            upvoteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//
//            downvoteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });


            String uri = "drawable/" + stringToAvatar(post.getAuthor()).toLowerCase();

            if (uri.equals("drawable/hacker") && post.getAuthor().equals("███████"))
                uri = "drawable/black_square";

            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

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
