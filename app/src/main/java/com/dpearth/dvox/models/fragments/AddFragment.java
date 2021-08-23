package com.dpearth.dvox.models.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        return rootView;
    }

        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            word_counter = getActivity().findViewById(R.id.word_counter);
            content_post = getActivity().findViewById(R.id.content_post);
            content_post.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    text = text.replace("  ", " ");
//                    text = text.replace("\n", " ");
//                    String[] textArray = text.split(" ");

                    String text = content_post.getText().toString();

                    int wordCounter = 0;

                    char ch[]= new char[text.length()];
                    for(int i=0;i<text.length();i++) {
                        ch[i]= text.charAt(i);
                        if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )
                            wordCounter++;
                    }

                    word_counter.setText("Words: " + wordCounter);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }

                

            });
        }

}