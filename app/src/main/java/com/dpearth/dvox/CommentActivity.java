package com.dpearth.dvox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dpearth.dvox.livedata.PostViewModel;
import com.dpearth.dvox.models.recycleviews.CommentAdapter;
import com.dpearth.dvox.models.recycleviews.PostAdapter;
import com.dpearth.dvox.smartcontract.Comment;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;

import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends Activity {

    private Post post;
    private String currentUsername;

    private TextView postTitle;
    private TextView postAuthor;
    private TextView postMessage;
    private TextView postHashtag;
    private TextView postUpvotes;
    private TextView postDownvotes;
    private ImageView postAvatar;

    private TextView newMessage;

    private List<Comment> allComments = new ArrayList<>();
    private List<Comment> allFinalComments = new ArrayList<>();

    private Comment shimmerComment = new Comment();
    {
        shimmerComment.setId(BigInteger.valueOf(0));
        shimmerComment.setCommentAuthor("███████");
        shimmerComment.setCommentMessage("████████████████████████████████████████████████████████████");
        shimmerComment.setCommentBan(false);
    }

    private boolean loadMore = false;
    private  boolean lastComment = false;

    private int realEndID = 0;

    private SwipeRefreshLayout swipeRefreshLayoutComment;

    private CommentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //Get the current post
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        post = (Post) bundle.getSerializable("Post");

        //Get the current username (author for new comments)
        SharedPreferences preferences = getSharedPreferences("usernamePrefs", MODE_PRIVATE);
        currentUsername = preferences.getString("usernamePrefs", "");

        //Log.d("CommentView", post.getMessage());

        //Assign all variables to their views
        postTitle = findViewById(R.id.acPostTitle);
        postAuthor = findViewById(R.id.acPostAuthor);
        postMessage = findViewById(R.id.acPostMessage);
        postHashtag = findViewById(R.id.acPostHashtag);
        postUpvotes = findViewById(R.id.acPostUpvotes);
        postDownvotes = findViewById(R.id.acPostDownvotes);
        postAvatar = findViewById(R.id.acPostAvatar);
        newMessage = findViewById(R.id.acNewCommentMessage);

        //Assign their values
        postTitle.setText(post.getTitle());
        postAuthor.setText(post.getAuthor());
        postMessage.setText(post.getMessage());
        postHashtag.setText(post.getHashtag());
        //TODO: add upvotes once Revaz is done with Votes class
        //TODO: add downvotes once Revaz is done with Votes class
        String uri = "drawable/" + stringToAvatar(post.getAuthor()).toLowerCase();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        postAvatar.setImageResource(imageResource);

        allFinalComments.add(new Comment(BigInteger.valueOf(1), "I am the author", "Hello!!", false));


        //Assign hint based on the current username
        newMessage.setHint("Comment as " + currentUsername);

        swipeRefreshLayoutComment = this.findViewById(R.id.swipeRefreshLayoutComment);
        swipeRefreshLayoutComment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setCommentCount(0);
                allFinalComments.clear();
                lastComment = false;
                swipeRefreshLayoutComment.setRefreshing(false);
            }
        });

        RecyclerView recyclerView = this.findViewById(R.id.acCommentsRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new CommentAdapter(allFinalComments);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                Log.d("loading", String.valueOf(lastCompletelyVisibleItemPosition));

                try {
                    if (lastCompletelyVisibleItemPosition == getCommentCount() - 1) {
                        Log.d("loading", "Loading more " + lastCompletelyVisibleItemPosition + " " + loadMore );
                        if (loadMore == true && lastComment == false) {
                            //addShimmer();
                            queryComments(6, getCommentCount(), (int) post.getId());
                        }

                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    /**
     * Retrieves the certain number of last comments from the smart contract.
     *
     * @param numberOfComments - the number of comments to get
     * @param currentId - the current comment id
     * @param postId - the postId
     */
    private void queryComments(int numberOfComments, int currentId, int postId) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // ################# GET ALL POSTS #################//
                SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

                while (preferences.getString("credentials", "error").equals("error") ||
                        preferences.getString("contractAddress", "error").equals("error") ||
                        preferences.getString("credentials", "error").equals("error")) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                SmartContract contract = new SmartContract(preferences);

                int postCount;
                int contractPosts = contract.getPostCount();

                if (currentId == -1)
                    postCount = contractPosts;
                else
                    postCount = contractPosts - currentId;

                Log.d("Post loader", "Trying to print... in total:" + postCount);

                if ( postCount > 0){
                    for (int i = postCount; i > postCount - numberOfComments; i--){
                        if (i > 0) {
                            Comment comment = contract.getComment(postId, i);
                            Log.i("Comment loader", "Comment:" + comment.toString());
                                allComments.add(comment);

                            loadMore = true;
                            //TODO Maybe add to database here?
//                            adapterVERSION2.setPosts(allPosts);
                        }
                    }
                };
                // ################# GET ALL POSTS #################//

                if (this != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //UPDATE UI

                            int tempCounter = 0;

                            if (isShimmer(allFinalComments.get(0)))
                                allFinalComments.remove(0);

                            for (Comment j: allComments) {
                                allFinalComments.add(j);
                                tempCounter++;
                                if (j.getId() == BigInteger.valueOf(1)){
                                    lastComment = true;
                                }
                            }
                            setCommentCount(getCommentCount() + tempCounter);
                            SharedPreferences.Editor editor = preferences.edit().putLong("postCount", Long.valueOf(realEndID));
                            editor.apply();
                            Log.d("realEndID", "Real end id: " + realEndID);

                        }
                    });
                }
            }
        });

        loadMore = false;
        allComments.clear();
        thread.start();
    }

    //TODO: Revaz, please convert those shimmer strings into static strings.
    private boolean isShimmer(Comment comment){
        if (comment.getCommentAuthor().equals("███████"))
            return true;
        return false;
    }

    private void addShimmer(){
       // Log.d("HelloShimmer", "Shimmer " + String.valueOf(adapter.shimmer));
       // shimmerComment.setId(getCommentCount() + 1);
        allFinalComments.add(shimmerComment);
    }

    private int getCommentCount(){
        //Get the latest id
        return realEndID;
    }

    private void setCommentCount(int i){
        realEndID = i;
    }

    private void loadMore(){
        if (getCommentCount() > 0)
            loadMore = true;
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
