package com.dpearth.dvox.models.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dpearth.dvox.R;
import com.dpearth.dvox.RandomNameGenerator;
import com.dpearth.dvox.databinding.ActivityMainBinding;
import com.dpearth.dvox.livedata.User;


public class UserFragment extends Fragment {
    private Button generateNewNameButton;
    private TextView generatedNameTextView;
    public static final String USERNAME_PREFS = "usernamePrefs";

    private String username;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_user_profile2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ActivityMainBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_main);



        /*  Button for Regenerating a new name  */
        generateNewNameButton = getActivity().findViewById(R.id.generate_new_name_button);
        generatedNameTextView = getActivity().findViewById(R.id.profile_username);



        generateNewNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

//        loadData();
//        updateViews();

    }

    public void initializeUsername() {

        SharedPreferences usernamePreferences = getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);

        if (usernamePreferences.getString(USERNAME_PREFS, "").equals("")) {
            String randomNameGenerator = RandomNameGenerator.getRandomlyGeneratedName();

            SharedPreferences.Editor usernamePreferencesEditor = usernamePreferences.edit().putString(USERNAME_PREFS, randomNameGenerator);
            RandomNameGenerator.checkAndAddGeneratedNameFireStore(randomNameGenerator, false);

            usernamePreferencesEditor.apply();

            Toast.makeText(getActivity(), "Data Initialized", Toast.LENGTH_SHORT).show();

        }

    }

    public void saveData() {

        String name = RandomNameGenerator.getRandomlyGeneratedName();
//        RandomNameGenerator.checkAndAddGeneratedNameFireStore(name, false);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit().putString(USERNAME_PREFS, name);

        editor.apply();

        Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);

        username = sharedPreferences.getString(USERNAME_PREFS, "Generate Username");

    }

    public void updateViews() {

        generatedNameTextView.setText(username);
    }


}

