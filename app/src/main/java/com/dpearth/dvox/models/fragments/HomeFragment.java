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
import android.widget.Toast;

import com.dpearth.dvox.CommentActivity;
import com.dpearth.dvox.LoginActivity;
import com.dpearth.dvox.R;
import com.dpearth.dvox.databinding.FragmentHomeBinding;
import com.dpearth.dvox.livedata.PostViewModel;
import com.dpearth.dvox.models.recycleviews.PostAdapter;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.muddzdev.styleabletoast.StyleableToast;

import org.json.JSONException;

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
    private Post shimmerPost = new Post();
    {
        shimmerPost.setTitle("██████████████");
        shimmerPost.setAuthor("███████");
        shimmerPost.setMessage("██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");
        shimmerPost.setHashtag("█████████");
    }

    private boolean loadMore = false;
    private  boolean lastPost = false;

    private int positionStart = 0;
    private int itemCount = 0;

    private int realEndID = 0;

    private int numberOfPostsToLoad = 8;

    private boolean refreshEnabled = false;

    private boolean thereIsShimmer = false;
    private boolean shimmerNeedstoBeDeleted = false;


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


        postViewModel = new ViewModelProvider(getActivity()).get(PostViewModel.class);

        adapter = new PostAdapter(this, allPosts);//getContext(), allPosts -> as pars

        recyclerView.setAdapter(adapter);

        if (postViewModel.getAllPosts().getValue() != null) {
            allPosts = postViewModel.getAllPosts().getValue();

        } else {
            realEndID = allPosts.size();

            if (realEndID == 0) {
                addShimmer();
                queryPosts(numberOfPostsToLoad, -1);
            }
        }

        swipeRefreshLayout = getActivity().findViewById(R.id
                .swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (refreshEnabled) {

                    int count = allPosts.size();
                    allPosts.clear();
                    postViewModel.deleteAllPosts();
                    adapter.notifyItemRangeRemoved(0, count);
                    realEndID = 0;
                    lastPost = false;
                    addShimmer();
                    queryPosts(numberOfPostsToLoad, -1);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                try {
                    if (lastCompletelyVisibleItemPosition == realEndID - 1) {
                        Log.d("loading", "Loading more " + lastCompletelyVisibleItemPosition + " " + loadMore );
                        if (realEndID != 0 && loadMore == true && lastPost == false) {
                            //addShimmer();
                            queryPosts(numberOfPostsToLoad, allPosts.size());
                        }

                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onResume() {
        if (!thereIsShimmer) {
            adapter.notifyItemRangeChanged(0, realEndID);
        }
        super.onResume();
    }

    /**
     * Retrieves the certain number of last posts from the smart contract.
     *
     * @param numberOfPosts - the number of posts to get
     */
    private void queryPosts(int numberOfPosts, int currentId) {

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

                int postCount;
                int contractPosts = contract.getPostCount();

                if (currentId == -1)
                    postCount = contractPosts;
                else
                    postCount = contractPosts - currentId;

                Log.d("Post loader", "Trying to print... in total:" + postCount);

                if ( postCount > 0){
                    for (int i = postCount; i > postCount - numberOfPosts; i--){
                        if (i > 0) {
                            Post post = contract.getPost(i);

                            if (!post.isBan()) {
                                if (thereIsShimmer) {
                                    allPosts.remove(0);
                                    postViewModel.insert(post);
                                    thereIsShimmer = false;
                                }
                                allPosts.add(post);
                                itemCount++;
                                //postViewModel.insert(post);
                            }

                            if (post.getId() == 1)
                                    lastPost = true;

                        }

                        realEndID = allPosts.size();
                        loadMore = true;
                    }
                };

                // ################# GET ALL POSTS #################//

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (shimmerNeedstoBeDeleted) {
                                adapter.notifyItemRemoved(0);
                                adapter.notifyItemRangeInserted(positionStart-1, itemCount);
                                shimmerNeedstoBeDeleted = false;
                            }
                            else
                                adapter.notifyItemRangeInserted(positionStart, itemCount);
                            refreshEnabled = true;

                        }
                    });
                }
            }
        });

        refreshEnabled = false;
        loadMore = false;
        itemCount = 0;
        positionStart = allPosts.size();
        thread.start();
    }

    private void addShimmer(){
        shimmerPost.setId(0);
        allPosts.add(shimmerPost);
        thereIsShimmer = true;
        shimmerNeedstoBeDeleted = true;
        adapter.notifyItemInserted(0);
    }

}