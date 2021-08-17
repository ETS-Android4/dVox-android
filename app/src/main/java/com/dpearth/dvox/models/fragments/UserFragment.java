package com.dpearth.dvox.models.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dpearth.dvox.R;
import com.dpearth.dvox.RandomNameGenerator;

import java.util.Set;


public class UserFragment extends Fragment {
    private Button generateNewNameButton;
    private TextView generatedNameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_user_profile2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*  Button for Regenerating a new name  */
        generateNewNameButton = getActivity().findViewById(R.id.generate_new_name_button);
        generateNewNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomNameGenerator.getRandomlyGeneratedName();
            }
        });

//        SharedPreferences.Editor prefsEditor;
//
//
//        SharedPreferences usernamePreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor usernamePreferencesEditor = usernamePreferences.edit().putString("UserName", "Rez-Master-Y");
//        usernamePreferencesEditor.commit();
//
//        usernamePreferences.getString("UserName");
//
//
//        /*  Displaying newly generated name  */
        generatedNameTextView = getActivity().findViewById(R.id.profile_username);
        generatedNameTextView.setText("Revaz");
    }


}

