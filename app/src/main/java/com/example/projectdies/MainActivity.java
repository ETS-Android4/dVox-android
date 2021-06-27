package com.example.projectdies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projectdies.models.Post;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_post); //Should be changed to main activity

        Post post1 = new Post("Welcome message","Creator", "Welcome to the app");


    }
}