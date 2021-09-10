package com.dpearth.dvox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dpearth.dvox.smartcontract.Post;
import com.muddzdev.styleabletoast.StyleableToast;

public class CommentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_section_of_post);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Post post = (Post) bundle.getSerializable("Post");

        Log.d("CommentView", post.getMessage());
    }
}
