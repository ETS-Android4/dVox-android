package com.dpearth.dvox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dpearth.dvox.smartcontract.Post;

import org.w3c.dom.Text;

public class CommentActivity extends Activity {

    private Post post;

    private TextView postTitle;
    private TextView postAuthor;
    private TextView postMessage;
    private TextView postHashtag;
    private TextView postUpvotes;
    private TextView postDownvotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //Get the current post
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        post = (Post) bundle.getSerializable("Post");

        //Log.d("CommentView", post.getMessage());

        //Assign all variables to their views
        postTitle = findViewById(R.id.acPostTitle);
        postAuthor = findViewById(R.id.acPostAuthor);
        postMessage = findViewById(R.id.acPostMessage);
        postHashtag = findViewById(R.id.acPostHashtag);
        postUpvotes = findViewById(R.id.acPostUpvotes);
        postDownvotes = findViewById(R.id.acPostDownvotes);

        //Assign their values
        postTitle.setText(post.getTitle());
        postAuthor.setText(post.getAuthor());
        postMessage.setText(post.getMessage());
        postHashtag.setText(post.getHashtag());
        //TODO: add upvotes once Revaz is done with Votes class
        //TODO: add downvotes once Revaz is done with Votes class
    }
}
