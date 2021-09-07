package com.dpearth.dvox;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class CommentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_section_of_post);
    }
}
