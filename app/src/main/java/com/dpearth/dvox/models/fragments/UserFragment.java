package com.dpearth.dvox.models.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dpearth.dvox.LoginActivity;
import com.dpearth.dvox.R;
import com.dpearth.dvox.databinding.FragmentUserBinding;
import com.dpearth.dvox.livedata.Statistics;
import com.dpearth.dvox.livedata.User;
import com.dpearth.dvox.username.Username;
import com.muddzdev.styleabletoast.StyleableToast;


public class UserFragment extends Fragment {
    private Button generateButton;
    private Button saveButton;
    private Button cancelButton;

    private TextView postCreated;
    private TextView postUpvoted;
    private TextView postDownvoted;
    private TextView postComments;

    private TextView generatedNameTextView;
    public static final String USERNAME_PREFS = "usernamePrefs";
    private String username;

    private FragmentUserBinding binding;

    private Username usernameInstance;

    private Statistics statistics;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        usernameInstance = new Username(getActivity());
        usernameInstance.retrieveUsername(true);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);
        String name = preferences.getString(USERNAME_PREFS, "");
        binding.setUser(new User(name));
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ActivityMainBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_main);

        /*  Button for Regenerating a new name  */
        generateButton = getActivity().findViewById(R.id.generate_button);
        saveButton = getActivity().findViewById(R.id.save_button);
        cancelButton = getActivity().findViewById(R.id.cancel_button);
        generatedNameTextView = getActivity().findViewById(R.id.profile_username);

        postCreated = getActivity().findViewById(R.id.postsCreated);
        postUpvoted = getActivity().findViewById(R.id.postsUpvoted);
        postDownvoted = getActivity().findViewById(R.id.postsDownvoted);
        postComments = getActivity().findViewById(R.id.commentsAdded);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regenerateUsername();
                toggleButtonsVisibility();

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmUsername();
                toggleButtonsVisibility();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abortRegeneration();
                toggleButtonsVisibility();
            }
        });

        loadData();
        updateViews();

    }


    private void confirmUsername(){
        usernameInstance.userNameConfirm();

        username = usernameInstance.getUsernameString();

        Log.d("Username", "generateName: " + username);

        binding.setUser(new User(username));

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().putString(USERNAME_PREFS, username);

        editor.apply();

        resetStatistics();

        StyleableToast.makeText(getActivity(), "Profile saved!", Toast.LENGTH_SHORT, R.style.LoginToast).show();
    }

    private void abortRegeneration(){
        usernameInstance.userNameAbort();

        username = usernameInstance.getUsernameString();
        Log.d("Username", "generateName: " + username);

        binding.setUser(new User(username));

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().putString(USERNAME_PREFS, username);

        editor.apply();
    }

    private void regenerateUsername() {

        usernameInstance.generateUsername(false);

        username = usernameInstance.getUsernameString();
        Log.d("Username", "generateName: " + username);

        binding.setUser(new User(username));

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);

        username = sharedPreferences.getString(USERNAME_PREFS, "Generate Username");

    }

    public void updateViews() {

        generatedNameTextView.setText(username);
    }

    public void toggleButtonsVisibility(){
        Log.d("Username", "Changing visibility...");
        if (generateButton.getVisibility() == View.VISIBLE){
            generateButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
        } else {
            generateButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
        }
    }

    public void resetStatistics(){
        statistics = new Statistics();
        statistics.resetStatistics();
        postCreated.setText("Posts created: 0");
        postUpvoted.setText("Posts upvoted: 0");
        postDownvoted.setText("Posts downvoted: 0");
        postComments.setText("Comments added: 0");
    }

    public void reloadStatistics(){
        statistics = new Statistics();
        postCreated.setText("Posts created: " + String.valueOf(statistics.getPostsCreated()));
        postUpvoted.setText("Posts upvoted: " + String.valueOf(statistics.getUpVoted()));
        postDownvoted.setText("Posts downvoted: " + String.valueOf(statistics.getDownVoted()));
        postComments.setText("Comments added: " + String.valueOf(statistics.getCommentsAdded()));
    }

}

