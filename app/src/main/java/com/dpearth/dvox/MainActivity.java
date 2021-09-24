package com.dpearth.dvox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dpearth.dvox.firebasedata.APIs;
import com.dpearth.dvox.livedata.PostViewModel;
import com.dpearth.dvox.models.fragments.ComposeFragment;
import com.dpearth.dvox.models.fragments.HomeFragment;
import com.dpearth.dvox.models.fragments.UserFragment;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.dpearth.dvox.username.Username;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private EditText postTitle, postTheme, postContent;
    private TextView postAuthor;
    private Button createButton;

    private Fragment activeFragment;

    private Fragment homeFragment = new HomeFragment();
    private Fragment composeFragment = new ComposeFragment();
    private Fragment userFragment = new UserFragment();




    private String currentFragment = "";
    
    public static final String USERNAME_PREFS = "usernamePrefs";


    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Delete all posts when the app is restarted
        new PostViewModel(getApplication()).deleteAllPosts();

        //Get shared preferences and put new APIs there
        SharedPreferences preferencesKeys = getSharedPreferences("pref", Context.MODE_PRIVATE);
        getAPIs(preferencesKeys);

        Username usernameInstance = new Username(this);
        usernameInstance.retrieveUsername(true);

//        fragmentManager.beginTransaction().add(R.id.fl_wrapper, homeFragment).hide(userFragment).commit();
//        fragmentManager.beginTransaction().add(R.id.fl_wrapper, composeFragment).hide(userFragment).commit();
//        fragmentManager.beginTransaction().add(R.id.fl_wrapper, userFragment).hide(userFragment).commit();



        super.onCreate(savedInstanceState);
        setTheme(R.style.LoginTheme_ProjectDIES);
        setContentView(R.layout.activity_main);

        //Initialize paper
        Paper.init(getApplicationContext());

        fragmentManager.beginTransaction().add(R.id.fl_wrapper, homeFragment, "home").hide(homeFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fl_wrapper, userFragment, "user").hide(userFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fl_wrapper, composeFragment, "compose").hide(composeFragment).commit();

        activeFragment = homeFragment;

        bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment;
                    switch (menuItem.getItemId()) {
                        case R.id.ic_add:
                            if (currentFragment != "compose") {
                                fragment = new ComposeFragment();
                                //fragmentManager.beginTransaction().hide(activeFragment).show(composeFragment).commit();
                                //if you added fragment via layout xml
                                ComposeFragment fragmentC = (ComposeFragment) fragmentManager.findFragmentById(R.id.fl_wrapper);
                                fragmentC.updateUsername();
                                fragmentManager.beginTransaction().hide(activeFragment).show(composeFragment).commit();
                                fragmentC.updateUsername();
                                activeFragment = composeFragment;
                                //fragmentManager.beginTransaction().add(R.id.fl_wrapper, activeFragment).commit();
                            }
                            currentFragment = "compose";
                            break;
                        case R.id.ic_user:
                            if (currentFragment != "user") {
                                fragment = new UserFragment();
                                //fragmentManager.beginTransaction().hide(activeFragment).show(userFragment).commit();
                                UserFragment fragmentU = (UserFragment) fragmentManager.findFragmentByTag("user");
                                fragmentU.reloadStatistics();
                                fragmentManager.beginTransaction().hide(activeFragment).show(userFragment).commit();
                                activeFragment = userFragment;
                                //fragmentManager.beginTransaction().add(R.id.fl_wrapper, activeFragment).commit();
                            }
                            currentFragment = "user";
                            break;
                        default:
                            if (currentFragment != "home") {
                                fragment = new HomeFragment();
                                //fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                                activeFragment = homeFragment;
                                //fragmentManager.beginTransaction().add(R.id.fl_wrapper, activeFragment).commit();
                            }
                            currentFragment = "home";
                            break;
                    }
                    //I think this needs to change into Home Fragment
                    return true;
                }
            });
            bottomNavigationView.setSelectedItemId(R.id.ic_home);
        }


    //Maybe this belongs in AddFragment class
    public void startCreatePostThread (View view){
        CreatePostThread createPostThread = new CreatePostThread();
        createPostThread.start();
    }

    class CreatePostThread extends Thread {

        CreatePostThread() {
        }

        @Override
        public void run() {

            //Fetching String values from ADD page
            createButton = findViewById(R.id.createButton);
            postTitle = findViewById(R.id.post_title);
            postTheme = findViewById(R.id.hashtag);
            postContent = findViewById(R.id.content_post);
            postAuthor = findViewById(R.id.post_author);
            createButton = findViewById(R.id.createButton);

            String title = postTitle.getText().toString();
            String hashtag = postTheme.getText().toString();
            String content = postContent.getText().toString();

            if (title.equals(""))
                shakeTitle();
            if (hashtag.equals(""))
                shakeHashtag();
            if (content.equals("")){
                shakeMessage();
            }

            if (!title.equals("") && !hashtag.equals("") && !content.equals("")) {
                createButton.setEnabled(false);

                String author = postAuthor.getText().toString();

                //<-- Create POST -->\\
                SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
                SmartContract smartContract = new SmartContract(preferences);
                smartContract.createPost(title, author, content, hashtag);

                postTheme.setText("");
                postTitle.setText("");
                postContent.setText("");

                createButton.setEnabled(true);
            }

            //Todo Toast "You have successfully Posted" or "R U sure about that post?"
        }
    }

    private void shakeMessage(){
        YoYo.with(Techniques.Shake).playOn(findViewById(R.id.content_post));

    }
    private void shakeTitle(){
        YoYo.with(Techniques.Shake).playOn(findViewById(R.id.post_title));

    }
    private void shakeHashtag(){
        YoYo.with(Techniques.Shake).playOn(findViewById(R.id.hashtag));
    }

    /**
     * Gets new API keys by resetting them and getting new.
     *
     * !!! SHOULD BE USED AT THE BEGINNING OF THE MAIN ACTIVITY
     *
     * @param preferences
     */
    private void getAPIs (SharedPreferences preferences){
        //Reset APIs
        resetAPIs(preferences);

        //Update APIs
        updateAPIs(preferences);
    }

    /**
     * Reset all APIs keys to update them.
     * @param preferences - Updated
     */
    private void resetAPIs (SharedPreferences preferences){
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
    private void updateAPIs (SharedPreferences preferences){
        new APIs(preferences);
    }

}

