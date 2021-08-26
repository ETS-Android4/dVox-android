package com.dpearth.dvox.models.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import com.dpearth.dvox.R;
import android.R.layout;

import com.dpearth.dvox.username.Username;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AddFragment extends Fragment {

     private TextView word_counter, post_author;
     private EditText content_post, hashtag;
     private ImageView imageView2;
     private Username usernameInstance;
     private String Avatar_string;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);
        usernameInstance = new Username(getActivity());
        usernameInstance.retrieveUsername(true);

        Avatar_string = "drawable/" + stringToAvatar(usernameInstance.getUsernameString()).toLowerCase();


        View rootView = inflater.inflate(R.layout.compose_fragment, container, false);
        return rootView;


    }

//    final Button button = getActivity(R.id.verify_button);
//         button.setOnClickListener(new View.OnClickListener() {
//
//             public void onClick(View v) {
//            // your handler code here
//        }
//    };

        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            word_counter = getActivity().findViewById(R.id.word_counter);
            content_post = getActivity().findViewById(R.id.content_post);
            hashtag = getActivity().findViewById(R.id.hashtag);
            post_author = getActivity().findViewById(R.id.post_author);
            imageView2 = getActivity().findViewById(R.id.avatar_addfragment);




            int imageResource =getActivity().getResources().getIdentifier(Avatar_string, null, getActivity().getPackageName());
            imageView2.setImageResource(imageResource);


            post_author.setText(usernameInstance.getUsernameString());





            // !!! Hashtag
//            String input = hashtag.getText().toString();
//
//            hashtag.setText("");
//
//            if (!input.startsWith("#")) {
//                hashtag.setText("#" + input);
//            }

//            if value.prefix(1) != "#" {
//                value = "#" + value
//            }



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



            hashtag.addTextChangedListener(new TextWatcher() {


                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {
//
//                    String input = hashtag.getText().toString();
//
//                    if (!input.startsWith("#")) {
//                        hashtag.setText("#" + input);
//                    }

                }


            });


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



