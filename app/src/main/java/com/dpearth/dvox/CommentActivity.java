package com.dpearth.dvox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dpearth.dvox.livedata.PostViewModel;
import com.dpearth.dvox.models.recycleviews.CommentAdapter;
import com.dpearth.dvox.models.recycleviews.PostAdapter;
import com.dpearth.dvox.smartcontract.Comment;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.muddzdev.styleabletoast.StyleableToast;

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

    private ImageView backButton;
    private Button postButton;

    private List<Comment> allComments = new ArrayList<>();

    private Comment shimmerComment = new Comment();
    {
        shimmerComment.setId(BigInteger.valueOf(0));
        shimmerComment.setCommentAuthor("███████");
        shimmerComment.setCommentMessage("████████████████████████████████████████████████████████████");
        shimmerComment.setCommentBan(false);
    }

    private boolean loadMore = false;
    private  boolean lastComment = false;

    private int positionStart = 0;
    private int itemCount = 0;

    private int realEndID = 0;

    private int numberOfCommentsToLoad = 8;

    private boolean refreshEnabled = false;

    private boolean thereIsShimmer = false;
    private boolean shimmerNeedstoBeDeleted = false;

    private SwipeRefreshLayout swipeRefreshLayoutComment;

    private CommentAdapter adapter;

    private RecyclerView recyclerView;

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

        backButton = findViewById(R.id.acBackButton);

        postButton = findViewById(R.id.acPostButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Assign their values
        postTitle.setText(post.getTitle());
        postAuthor.setText(post.getAuthor());
        postMessage.setText(post.getMessage());
        postHashtag.setText(post.getHashtag());

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createComment(currentUsername, newMessage.getText().toString(), (int) post.getId());
            }
        });

        //TODO: add upvotes once Revaz is done with Votes class
        //TODO: add downvotes once Revaz is done with Votes class
        String uri = "drawable/" + stringToAvatar(post.getAuthor()).toLowerCase();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        postAvatar.setImageResource(imageResource);

        //Assign hint based on the current username
        newMessage.setHint("Comment as " + currentUsername);

        swipeRefreshLayoutComment = this.findViewById(R.id.swipeRefreshLayoutComment);
        swipeRefreshLayoutComment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                realEndID = 0;
                lastComment = false;
                swipeRefreshLayoutComment.setRefreshing(false);
            }
        });

        allComments = new ArrayList<>();

        recyclerView = this.findViewById(R.id.acCommentsRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CommentAdapter(allComments);//getContext(), allPosts -> as pars

        recyclerView.setAdapter(adapter);

        realEndID = allComments.size();

        if (realEndID == 0) {
            //addShimmer();
            queryComments(numberOfCommentsToLoad, -1);
        }


        swipeRefreshLayoutComment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (refreshEnabled) {

                    int count = allComments.size();
                    allComments.clear();
                    adapter.notifyItemRangeRemoved(0, count);
                    realEndID = 0;
                    lastComment = false;
                    //addShimmer();
                    queryComments(numberOfCommentsToLoad, -1);
                }
                swipeRefreshLayoutComment.setRefreshing(false);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                Log.d("loading", String.valueOf(lastCompletelyVisibleItemPosition));

                try {
                    if (lastCompletelyVisibleItemPosition == realEndID - 1) {
                        Log.d("loading", "Loading more " + lastCompletelyVisibleItemPosition + " " + loadMore );
                        if (loadMore == true && lastComment == false) {
                            //addShimmer();
                            queryComments(6, realEndID);
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
     * @param currentId - the latest comment id
     */
    private void queryComments(int numberOfComments, int currentId) {

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

                int commentCount;
                int contractPosts = contract.getPost(post.getId()).getCommentCount();

                if (currentId == -1)
                    commentCount = contractPosts;
                else
                    commentCount = contractPosts - currentId;

                Log.d("Post loader", "Trying to print... in total:" + commentCount);

                if ( commentCount > 0){
                    for (int i = commentCount; i > commentCount - numberOfComments; i--){
                        if (i > 0) {
                            Comment comment = contract.getComment(post.getId(), i);

                            if (!comment.getCommentBan()) {
                                if (thereIsShimmer) {
                                    allComments.remove(0);
                                    thereIsShimmer = false;
                                }
                                allComments.add(comment);
                                itemCount++;
                            }

                            if (post.getId() == 1)
                                lastComment = true;

                        }

                        realEndID = allComments.size();
                        loadMore = true;
                    }
                };


                // ################# GET ALL POSTS #################//

                if (this != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (shimmerNeedstoBeDeleted) {
                                adapter.notifyItemRemoved(0);
                                adapter.notifyItemRangeInserted(positionStart-1, itemCount);
                                shimmerNeedstoBeDeleted = false;
                            }
                            else
                                adapter.notifyItemRangeInserted(positionStart, itemCount);
                            refreshEnabled = true;

                        }
                    });
                }
            }
        });

        refreshEnabled = false;
        loadMore = false;
        itemCount = 0;
        positionStart = allComments.size();
        thread.start();
    }

    private void addShimmer(){
        shimmerComment.setId(BigInteger.valueOf(0));
        allComments.add(shimmerComment);
        thereIsShimmer = true;
        shimmerNeedstoBeDeleted = true;
        adapter.notifyItemInserted(0);
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

    private void createComment(String author, String message, int id) {

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

                realEndID++;

                contract.createComment(author, message, id);



                // ################# GET ALL POSTS #################//


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        postButton.setEnabled(true);
                        postButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.BlackColor));
                    }
                });
            }

        });

        if (!message.equals("") && !author.equals("")) {
            postButton.setEnabled(false);
            postButton.setTextColor(ContextCompat.getColor(this, R.color.TransparentWhiteColor));
            Comment tempComment = new Comment();
            tempComment.setId(BigInteger.valueOf(id));
            tempComment.setCommentAuthor(author);
            tempComment.setCommentMessage(message);
            allComments.add(0, tempComment);
            adapter.notifyItemInserted(0);
            recyclerView.scrollToPosition(0);
            newMessage.setText("");
            thread.start();
        } else{
            shakeMessage();
        }
    }

    private void shakeMessage(){
        YoYo.with(Techniques.Shake).playOn(this.findViewById(R.id.acNewCommentMessage));

    }
}
