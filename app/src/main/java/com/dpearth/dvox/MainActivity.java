package com.dpearth.dvox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dpearth.dvox.firebasedata.APIs;
import com.dpearth.dvox.livedata.User;
import com.dpearth.dvox.models.fragments.AddFragment;
import com.dpearth.dvox.models.fragments.HomeFragment;
import com.dpearth.dvox.models.fragments.UserFragment;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.dpearth.dvox.username.Username;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText postTitle, postTheme, postContent;
    private Button buttonSave;

    public static final String USERNAME_PREFS = "usernamePrefs";


    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        //Get shared preferences and put new APIs there
        SharedPreferences preferencesKeys = getSharedPreferences("pref", Context.MODE_PRIVATE);
        getAPIs(preferencesKeys);

        SharedPreferences preferencesUsernames = getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.ic_add:
                        fragment = new AddFragment();
                        break;
                    case R.id.ic_home:
                        fragment = new HomeFragment();

//                        //Testing username in home fragment
//                        String a = preferencesUsernames.getString(USERNAME_PREFS, "");
//                        Log.d("USERNAME IN OTHER ACTIVITY", "username is: " + a);
                        break;
                    case R.id.ic_user:
                        fragment = new UserFragment();
                        break;
                    default:
                        fragment = new HomeFragment();
                        break;
                }
                //I think this needs to change into Home Fragment
                fragmentManager.beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.ic_home);

//
//        setContentView(R.layout.custome_design);
//
//        posts.add(new Post("hi", "Revaz", "asda", "#dsa"));
//
////      Lookup the recyclerview in activity layout
//        rvPosts = findViewById(R.id.rvPosts);
////        Initialize post
//        posts = Post.createPostList(5);
////        Create adapter passing in the sample user data
//        RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.this, posts);
////        Attach the adapter to the recyclerview to populate items
//        rvPosts.setAdapter(adapter);
////        Set layout manager to position the items
//        rvPosts.setLayoutManager(new LinearLayoutManager(this));
    }




    //Maybe this belongs in AddFragment class
    public void startCreatePostThread(View view){
        CreatePostThread createPostThread = new CreatePostThread();
        createPostThread.start();
    }

    public void startGetPostThread(View view){
        GetPostThread getPostThread = new GetPostThread();
        getPostThread.start();
    }


    class CreatePostThread extends Thread {

        CreatePostThread(){
        }

        @Override
        public void run() {

            //Fetching String values from ADD page
            postTitle = findViewById(R.id.post_title);
            postTheme = findViewById(R.id.post_theme);
            postContent = findViewById(R.id.content_post);
            buttonSave = findViewById(R.id.publich_post);

            String title = postTitle.getText().toString();
            String theme = postTheme.getText().toString();
            String content = postContent.getText().toString();

            //<-- Create POST -->\\
            SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
            SmartContract smartContract = new SmartContract(preferences);
            smartContract.createPost(title, "Revaz", content, theme);

            //Clearing fields after Posing

            //Toast "You have successfully Posted


//          System.out.println("Last POST: " + smartContract.getPost(smartContract.getPostCount() + 1).toString());
        }
    }

    class GetPostThread extends Thread {

        public GetPostThread() {

        }

        @Override
        public void run() {
            HomeFragment fragment = new HomeFragment();
        }
    }






    /**
     * Gets new API keys by resetting them and getting new.
     *
     * !!! SHOULD BE USED AT THE BEGINNING OF THE MAIN ACTIVITY
     *
     * @param preferences
     */
    private void getAPIs(SharedPreferences preferences){
        //Reset APIs
        resetAPIs(preferences);

        //Update APIs
        updateAPIs(preferences);
    }

    /**
     * Reset all APIs keys to update them.
     * @param preferences - Updated
     */
    private void resetAPIs(SharedPreferences preferences){
        //Reset APIs
        SharedPreferences.Editor prefsEditor = preferences.edit();

        prefsEditor.putString("Credentials", "error");
        prefsEditor.putString("ContractAddress", "error");
        prefsEditor.putString("InfuraURL", "error");

        prefsEditor.commit();
    }

    /**
     * Gets new API keys.
     * @param preferences
     */
    private void updateAPIs(SharedPreferences preferences){
        new APIs(preferences);
    }

}