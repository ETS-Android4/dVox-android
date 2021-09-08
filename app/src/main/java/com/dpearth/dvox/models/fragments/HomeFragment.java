package com.dpearth.dvox.models.fragments;


import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;

import com.dpearth.dvox.CommentActivity;
import com.dpearth.dvox.R;
import com.dpearth.dvox.databinding.FragmentHomeBinding;
import com.dpearth.dvox.livedata.PostViewModel;
import com.dpearth.dvox.models.recycleviews.PostAdapter;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    private List<Post> allPosts;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FragmentHomeBinding binding;
    private PostViewModel postViewModel;
    private PostAdapter adapter;

    private ImageView commentIcon;

//    private EndlessRecyclerViewScrollListener scrollListener;

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

        postViewModel = new ViewModelProvider(getActivity()).get(PostViewModel.class);

        adapter = new PostAdapter(getContext(), allPosts);//getContext(), allPosts -> as pars
        recyclerView.setAdapter(adapter);



        //todo when refresh apply change

        //        ViewMOdelProviders.of(this) ... is no longer supported >:-(
        postViewModel.getAllPosts().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            //This will get triggered everytime live data changes
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                adapter.setPosts(posts);
            }
        });

        postViewModel.getAllPosts().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                if (posts.size() == 0)
                    queryPostsVERSION2(6);
            }
        });

        swipeRefreshLayout = getActivity().findViewById(R.id
                .swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                postViewModel.deleteAllPosts();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


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
                    for (int i = postCount; i > postCount - numberOfPosts - 1; i--){
                        if (i > 0) {
                            Post post = contract.getPost(i);
                            Log.i("Post loader", "Post:" + post.getId());
                            allPosts.add(post);
                            //TODO Maybe add to database here?
//                            adapterVERSION2.setPosts(allPosts);
                        }
                    }
                };
                // ################# GET ALL POSTS #################//

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //UPDATE UI
                            for (Post j: allPosts) {
                                postViewModel.insert(j);
                            }

                        }
                    });
                }
            }
        });
        allPosts.clear();
        thread.start();
    }

//    private void goToCommentSection(){
//
//        commentIcon = getView().findViewById(R.id.version2_comment_button);
////        commentIcon = getActivity().findViewById(R.id.comment_button);
//
//        commentIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCommentActivity();
//            }
//        });
//    }

    private void openCommentActivity(){
        Intent intent = new Intent(getActivity(), CommentActivity.class);
        startActivity(intent);
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.comment_button){
//            openCommentActivity();
//        }
//    }
}