package com.dpearth.dvox.models.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dpearth.dvox.LoginActivity;
import com.dpearth.dvox.R;
import android.R.layout;
import android.widget.Toast;

import com.dpearth.dvox.livedata.Statistics;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.dpearth.dvox.username.Username;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.regex.Pattern;


public class ComposeFragment extends Fragment {

     private TextView word_counter, authorView;
     private EditText titleView, messageView, hashtagView;
     private ImageView imageView2;
     private Username usernameInstance;
     private String Avatar_string;
     private Button create_button;

     private CheckBox alertCheckBox;
     private Button alertSendButton;
     private Button alertCancelButton;

     private SharedPreferences checkboxPrefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);
        usernameInstance = new Username(getActivity());
        usernameInstance.retrieveUsername(true);
        Avatar_string = "drawable/" + stringToAvatar(usernameInstance.getUsernameString()).toLowerCase();
        checkboxPrefs = getContext().getSharedPreferences("dontShow", Context.MODE_PRIVATE);
        View rootView = inflater.inflate(R.layout.compose_fragment, container, false);
        return rootView;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            word_counter = getActivity().findViewById(R.id.word_counter);
            titleView = getActivity().findViewById(R.id.post_title);
            messageView = getActivity().findViewById(R.id.content_post);
            hashtagView = getActivity().findViewById(R.id.hashtag);
            authorView = getActivity().findViewById(R.id.post_author);
            imageView2 = getActivity().findViewById(R.id.avatar_addfragment);
            create_button = getActivity().findViewById(R.id.createButton);

            int imageResource =getActivity().getResources().getIdentifier(Avatar_string, null, getActivity().getPackageName());
            imageView2.setImageResource(imageResource);

            authorView.setText(usernameInstance.getUsernameString());

            messageView.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String text = messageView.getText().toString();

                    int wordCounter = 0;

                    char ch[]= new char[text.length()];
                    for(int i=0;i<text.length();i++) {
                        ch[i]= text.charAt(i);
                        if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )
                            wordCounter++;

                    }

                    word_counter.setText(wordCounter + " words");
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            hashtagView.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }


                @Override
                public void afterTextChanged(Editable text) {
                    Selection.setSelection(hashtagView.getText(), hashtagView.getText().length());


                    if(!text.toString().startsWith("#") && text.length() != 0){
                        hashtagView.setText("#" + text);
                        Selection.setSelection(hashtagView.getText(), hashtagView.getText().length());
                    }

                }


            });

            create_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (titleView.getText().toString().equals("")  || hashtagView.getText().toString().equals("") || messageView.getText().toString().equals("")) {
                        shakeTitle();
                        shakeHashtag();
                        shakeMessage();
                    }
                    else {
                        SharedPreferences.Editor prefsEditor = checkboxPrefs.edit();

                        if(checkboxPrefs.getBoolean("dontShow", false))
                            createPost(titleView.getText().toString(), authorView.getText().toString(), messageView.getText().toString(), hashtagView.getText().toString());
                        else {
                            final Dialog alert = new Dialog((getActivity()));
                            alert.setContentView(R.layout.activity_compose_alert);
                            alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            alertCancelButton = alert.findViewById(R.id.cancelButton);
                            alertSendButton = alert.findViewById(R.id.sendButton);
                            alertCheckBox = alert.findViewById(R.id.alertCheckbox);


                            alertCancelButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alert.cancel();
                                }
                            });
                            alertSendButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    createPost(titleView.getText().toString(), authorView.getText().toString(), messageView.getText().toString(), hashtagView.getText().toString());
                                }
                            });
                            alertCheckBox.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (checkboxPrefs.getBoolean("dontShow", false))
                                        prefsEditor.putBoolean("dontShow", false).commit();
                                    else
                                        prefsEditor.putBoolean("dontShow", true).commit();
                                }
                            });

                            alert.show();
                        }
                    }
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

    /**
     * Creates a new post.
     *
     * @param title - string title of the post
     * @param author - string author of the post
     * @param message - string message of the post
     * @param hashtag - string hashtag of the post
     */
    private void createPost(String title, String author, String message, String hashtag) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // ################# GET ALL POSTS #################//
                SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

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

                contract.createPost(title, author, message, hashtag);

                // ################# GET ALL POSTS #################//

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            titleView.setText("");
                            messageView.setText("");
                            //todo: add hashtag logic here
                            hashtagView.setText("");
                            StyleableToast.makeText(getActivity(), "Your post is sent! It will appear in our decentralized storage soon.", Toast.LENGTH_LONG, R.style.LoginToast).show();
                            Statistics statistics = new Statistics();
                            statistics.upUpVoted();
                            create_button.setEnabled(true);
                            create_button.setTextColor(ContextCompat.getColor(getContext(), R.color.BlackColor));
                        }
                    });
                }
            }
        });

        create_button.setEnabled(false);
        create_button.setTextColor(ContextCompat.getColor(getContext(), R.color.TransparentWhiteColor));
        thread.start();
    }

    private void shakeMessage(){
        YoYo.with(Techniques.Shake).playOn(getActivity().findViewById(R.id.content_post));

    }
    private void shakeTitle(){
        YoYo.with(Techniques.Shake).playOn(getActivity().findViewById(R.id.post_title));

    }
    private void shakeHashtag(){
        YoYo.with(Techniques.Shake).playOn(getActivity().findViewById(R.id.hashtag));
    }

    public void updateUsername(){
        usernameInstance = new Username(getActivity());
        usernameInstance.retrieveUsername(false);
        Avatar_string = "drawable/" + stringToAvatar(usernameInstance.getUsernameString()).toLowerCase();
        int imageResource =getActivity().getResources().getIdentifier(Avatar_string, null, getActivity().getPackageName());
        imageView2.setImageResource(imageResource);
        authorView.setText(usernameInstance.getUsernameString());
    }
}



