package com.example.projectdies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projectdies.models.Post;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Post post1 = new Post("Creator", "Welcome to the app");


    }
}