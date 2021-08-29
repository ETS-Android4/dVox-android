package com.dpearth.dvox.models.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    private RecyclerView rvPosts;
    private PostAdapter postAdapter;
    private List<Post> allPosts;

    private SwipeRefreshLayout swipeRefreshLayout;

    public static final String POST_TITLE = "postTitle";
    private FragmentHomeBinding binding;


    private PostViewModel postViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(POST_TITLE, Context.MODE_PRIVATE);
        String title = preferences.getString(POST_TITLE, "");

        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView recyclerView = getActivity().findViewById(R.id.liveDataRecycleView);//TODO Uncomment rvPosts;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final PostAdapterVERSION2 adapter = new PostAdapterVERSION2();
        recyclerView.setAdapter(adapter);

        //ViewMOdelProviders.of(this) ... is no longer supported >:-(
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getAllPosts().observe(getActivity(), new Observer<List<Post>>() {

            //This will get triggered everytime live data changes
            @Override
            public void onChanged(List<Post> posts) {
                adapter.setPosts(posts);
            }
        });




//        rvPosts = view.findViewById(R.id.rvPosts);
//        allPosts = new ArrayList<>();
//
//        postAdapter = new PostAdapter(getContext(), allPosts);
//        rvPosts.setAdapter(postAdapter);
//        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
//
//
//        swipeRefreshLayout = getActivity().findViewById(R.id
//                .swipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                allPosts.clear();
//
//                //On refresh
//                allPosts.add(new Post(1000, "refresh title", "Rezoie", "Irmebi moprinaven","koka kola"));
//                allPosts.add(new Post(1001, "refresh title 2", "Rezoie", "gilocav axal wels","koka kola"));
//                allPosts.add(new Post(1002, "refresh title 3", "Rezoie", "Irmebi xtian","koka kola"));
//                allPosts.add(new Post(1003, "refresh title 4", "Rezoie", "dronebis testi","koka kola"));
//                allPosts.add(new Post(1004, "refresh title 5", "Rezoie", "kvazi modo","koka kola"));
//
//                postAdapter.notifyDataSetChanged();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//
//        queryPosts(6);
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