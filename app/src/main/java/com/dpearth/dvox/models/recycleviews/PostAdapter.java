package com.dpearth.dvox.models.recycleviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.dpearth.dvox.livedata.VotesDictionary;
import com.dpearth.dvox.smartcontract.Post;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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

    public void updateVotesContainer(){

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
        switch(holder.getItemViewType()){
            case 0:
                holder.bind(post);
            case 1:
                break;
        }

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

        private int postId;
        private int postVoted;

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

        private Votes votes;
        private boolean upVoted;
        private boolean downVoted;


        private Observer votesChanged = new Observer() {
            @Override
            public void update(Observable o, Object newValue) {
                upvotes.setText(String.valueOf(votes.getUpvotes()));
                downvotes.setText(String.valueOf(votes.getDownvotes()));
            }
        };

        private VotesDictionary votesDictionary;

        private Observer votesDictionaryChanged = new Observer(){
            @Override
            public void update(Observable o, Object newValue){
                if (votesDictionary.getVote(postId) == 1) {
                    upVoted = true;
                    upvoteButton.setEnabled(true);
                    downvoteButton.setEnabled(false);
                    upvoteButton.setImageResource(R.drawable.fi_rr_thumbs_up_filled);
                }
                else if (votesDictionary.getVote(postId) == -1) {
                    downVoted = true;
                    upvoteButton.setEnabled(false);
                    downvoteButton.setEnabled(true);
                    downvoteButton.setImageResource(R.drawable.fi_rr_thumbs_down_filled);
                } else if (votesDictionary.getVote(postId) == 0){
                    upvoteButton.setEnabled(true);
                    downvoteButton.setEnabled(true);
                    upvoteButton.setImageResource(R.drawable.fi_rr_thumbs_up);
                    downvoteButton.setImageResource(R.drawable.fi_rr_thumbs_down);
                    upVoted = false;
                    downVoted = false;
                }
            }
        };

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

            postId = (int) post.getId();

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


            String uri = "drawable/" + stringToAvatar(post.getAuthor()).toLowerCase();

            if (uri.equals("drawable/hacker") && post.getAuthor().equals("███████"))
                uri = "drawable/black_square";

            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

            tvAvatar.setImageResource(imageResource);

            votes = new Votes(post.getId());
            votes.setVotes();
            votes.addObserver(votesChanged);

            votesDictionary = new VotesDictionary(context);
            votesDictionary.addObserver(votesDictionaryChanged);

            if (votesDictionary.getVote(postId) == 1) {
                upVoted = true;
                upvoteButton.setEnabled(true);
                downvoteButton.setEnabled(false);
                upvoteButton.setImageResource(R.drawable.fi_rr_thumbs_up_filled);
                downvoteButton.setImageResource(R.drawable.fi_rr_thumbs_down);
            }
            else if (votesDictionary.getVote(postId) == -1) {
                downVoted = true;
                upvoteButton.setEnabled(false);
                downvoteButton.setEnabled(true);
                upvoteButton.setImageResource(R.drawable.fi_rr_thumbs_up);
                downvoteButton.setImageResource(R.drawable.fi_rr_thumbs_down_filled);
            }

            upvoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!upVoted) {
                        votes.upVote(1);
                        votesDictionary.addVote(postId, 1);
                    } else{
                        votes.upVote(-1);
                        votesDictionary.addVote(postId, 0);
                    }
                }
            });

            downvoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!downVoted) {
                        votes.downVote(1);
                        votesDictionary.addVote(postId, -1);
                    } else{
                        votes.downVote(-1);
                        votesDictionary.addVote(postId, 0);
                    }
                }
            });
        }


        public void updateVotesContainer(){
            if (votesDictionary.getVote(postId) == 1) {
                upVoted = true;
                upvoteButton.setEnabled(true);
                downvoteButton.setEnabled(false);
                upvoteButton.setImageResource(R.drawable.fi_rr_thumbs_up_filled);
            }
            else if (votesDictionary.getVote(postId) == -1) {
                downVoted = true;
                upvoteButton.setEnabled(false);
                downvoteButton.setEnabled(true);
                downvoteButton.setImageResource(R.drawable.fi_rr_thumbs_down_filled);
            }
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
