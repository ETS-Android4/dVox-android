package com.dpearth.dvox.models.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.dpearth.dvox.R;
import android.R.layout;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AddFragment extends Fragment {

     TextView word_counter;
     EditText content_post;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.compose_fragment, container, false);


        word_counter = container.findViewById(R.id.word_counter);
        content_post = container.findViewById(R.id.content_post);

        content_post.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String text = content_post.getText().toString();
                    text = text.replace("\n"," ");
                    String[] textArray = text.split(" ");
                    word_counter.setText("Words: " + textArray.length);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Button button = (Button) inflater.inflate(R.layout.compose_fragment, container, false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // your handler code here
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}