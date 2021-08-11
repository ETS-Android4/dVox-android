package com.dpearth.dvox.models.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dpearth.dvox.MainActivity;
import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.google.gson.Gson;
import com.kenai.jffi.Main;

import org.web3j.crypto.Credentials;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";


//  private RecyclerView rvPosts;
    private RecyclerAdapter adapter;
    private ArrayList<Post> posts;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String cred = preferences.getString("Credentials", "error");
        Log.d(TAG, cred);

        SmartContract contract = new SmartContract(preferences);

        Log.d(TAG, contract.getPostCount().toString());

//        setContentView(R.layout.custome_design);
//
////      Lookup the recyclerview in activity layout
//        RecyclerView recvPosts = (RecyclerView) findViewById(R.id.cardview_clayout);
//
//
////        Initialize post
//        posts = Post.createPostList(5);
////        Create adapter passing in the sample user data
//        RecyclerAdapter adapter = new RecyclerAdapter(posts);
////        Attach the adapter to the recyclerview to populate items
//        recvPosts.setAdapter(adapter);
////        Set layout manager to position the items
//        recvPosts.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private String getAddress(Credentials credentials) {
        return credentials.getAddress();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}