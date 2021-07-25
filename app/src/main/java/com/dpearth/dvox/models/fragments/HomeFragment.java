package com.dpearth.dvox.models.fragments;

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

import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.Post;

import org.web3j.crypto.Credentials;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";


    private RecyclerView rvPosts;
    RecyclerAdapter adapter;

    ArrayList<Post> posts;

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

//        setContentView(R.layout.);

//        // Lookup the recyclerview in activity layout
//        RecyclerView recvPosts = (RecyclerView) findViewById(R.id.rvPosts);
//
//        // Initialize contacts
//        posts = Post.createPostList(5);
//        // Create adapter passing in the sample user data
//        RecyclerAdapter adapter = new RecyclerAdapter(posts);
//        // Attach the adapter to the recyclerview to populate items
//        recvPosts.setAdapter(adapter);
//        // Set layout manager to position the items
//        recvPosts.setLayoutManager(new LinearLayoutManager(getContext()));


        //See Smart Contract & Post in MainActivity.java

    }

    private String getAddress(Credentials credentials) {
        return credentials.getAddress();
    }

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

//        queryPosts();
    }

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        rvPosts = view.findViewById(R.id.rvPosts);
//
//
//        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new RecyclerAdapter(getContext(), monthNames);
//        rvPosts.setAdapter(adapter);
//    }

//    protected void queryPosts(String title, String author, String message, String hashtag) {
//        Post post = new Post();
//        post.setTitle(title);
//        post.setAuthor(author);
//        post.setMessage(message);
//        post.setHashtag(hashtag);
//    }



//    protected void queryPosts() {
//        ParseQuery<Post> query =  ParseQuery.getQuery(Post.class);
//        query.include(Post.DEFAULT_PIN);
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting posts", e);
//                    return;
//                }
//                for (Post post: posts) {
//                    Log.e(TAG, "Post: " + post.toString() + " , username: " + post.getAuthor());
//                }
//            }
//        });
//    }
}