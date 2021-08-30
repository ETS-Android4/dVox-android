package com.dpearth.dvox.models.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dpearth.dvox.R;
import com.dpearth.dvox.databinding.FragmentHomeBinding;
import com.dpearth.dvox.livedata.PostViewModel;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private List<Post> allPosts;

    private SwipeRefreshLayout swipeRefreshLayout;

    private FragmentHomeBinding binding;


    private PostViewModel postViewModel;

    public static final String EXTRA_TITLE = "com.dpearth.dvox.models.fragments.EXTRA_TITLE";
    public static final String EXTRA_AUTHOR = "com.dpearth.dvox.models.fragments.EXTRA_AUTHOR";
    public static final String EXTRA_MESSAGE = "com.dpearth.dvox.models.fragments.EXTRA_MESSAGE";
    public static final String EXTRA_HASHTAG = "com.dpearth.dvox.models.fragments.EXTRA_HASHTAG";

    private PostAdapterVERSION2 adapterVERSION2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allPosts = new ArrayList<>();

        RecyclerView recyclerView = getActivity().findViewById(R.id.liveDataRecycleView);//TODO Uncomment rvPosts;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapterVERSION2 = new PostAdapterVERSION2();//getContext(), allPosts -> as pars
        recyclerView.setAdapter(adapterVERSION2);


//        ViewMOdelProviders.of(this) ... is no longer supported >:-(
        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
//        adapterVERSION2.setPosts(allPosts);

//        postViewModel.getAllPosts().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
//
//            //This will get triggered everytime live data changes
//            @Override
//            public void onChanged(@Nullable List<Post> posts) {
//                adapterVERSION2.setPosts(posts);
////                Toast.makeText(getActivity(), "data displayed", Toast.LENGTH_SHORT).show();
//            }
//        });

        queryPostsVERSION2(6);
    }


    /**
     * Retrieves the certain number of last posts from the smart contract.
     *
     * @param numberOfPosts - the number of posts to get
     */
    private void queryPostsVERSION2(int numberOfPosts) {

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

                Log.d("Post loader", "Trying to print... in total:" + postCount);

                if ( postCount > 0){
                    for (int i = postCount; i > postCount - numberOfPosts; i--){
                        if (i > 0) {
                            Post post = contract.getPost(i);
                            Log.i("Post loader", "Post:" + post.toString());
                            allPosts.add(post);
                            //TODO Maybe add to database here?
                            adapterVERSION2.setPosts(allPosts);
                        }
                    }
                };
                // ################# GET ALL POSTS #################//

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //UPDATE UI
                            adapterVERSION2.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        thread.start();
    }

    public void saveLiveData(Post post) {
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, post.getTitle());
        data.putExtra(EXTRA_AUTHOR, post.getAuthor());
        data.putExtra(EXTRA_MESSAGE, post.getMessage());
        data.putExtra(EXTRA_HASHTAG, post.getHashtag());

    }
}