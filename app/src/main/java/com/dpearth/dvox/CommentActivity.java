package com.dpearth.dvox;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dpearth.dvox.smartcontract.Post;

import org.w3c.dom.Text;

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

        //Assign hint based on the current username
        newMessage.setHint("Comment as " + currentUsername);
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
