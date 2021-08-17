package com.dpearth.dvox.models.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dpearth.dvox.LoginActivity;
import com.dpearth.dvox.R;
import com.dpearth.dvox.RandomNameGenerator;


public class UserFragment extends Fragment {
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_user_profile2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = getActivity()//.findViewById(R.layout.activity_user_profile2)
                .findViewById(R.id.generate_new_name_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomNameGenerator.getRandomlyGeneratedName();
            }
        });
    }
}

