package com.dpearth.dvox.models.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dpearth.dvox.MainActivity;
import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private RecyclerView rvPosts;
    private PostAdapter postAdapter;
    private List<Post> allPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);
        allPosts = new ArrayList<>();

        postAdapter = new PostAdapter(getContext(), allPosts);
        rvPosts.setAdapter(postAdapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts(5);
    }

    /**
     * Retrieves the certain number of last posts from the smart contract.
     *
     * @param numberOfPosts - the number of posts to get
     */
    private void queryPosts(int numberOfPosts) {

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

                int postCount = contract.getPostCount();

                Log.i("Post loader", "Trying to print... in total:" + postCount);

                if ( postCount > 0){
                    for (int i = postCount; i > postCount - numberOfPosts; i--){
                        if (i > 0) {
                            Post post = contract.getPost(i);
                            Log.i("Post loader", "Post:" + post.toString());
                            allPosts.add(post);
                        }
                    }
                };
                // ################# GET ALL POSTS #################//

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //UPDATE UI
                            postAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        thread.start();
    }
}

//################# EXAMPLE OF BACKGROUND THREAD #################//
//
//    Thread thread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            //PERFORM BACKGROUND ACTION
//
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    //UPDATE UI ACTION
//                }
//            });
//        }
//    });
//
//    thread.start();
//
//################# EXAMPLE OF BACKGROUND THREAD #################//