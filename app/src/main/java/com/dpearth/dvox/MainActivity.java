package com.dpearth.dvox;

import static com.dpearth.dvox.RandomNameGenerator.getRandomlyGeneratedName;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

<<<<<<< Updated upstream
import com.dpearth.dvox.models.fragments.AddFragment;
import com.dpearth.dvox.models.fragments.HomeFragment;
import com.dpearth.dvox.models.fragments.UserFragment;
import com.dpearth.dvox.R;
import com.dpearth.dvox.models.fragments.UserProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

=======
import com.dpearth.dvox.firebasedata.APIs;
import com.dpearth.dvox.livedata.PostViewModel;
import com.dpearth.dvox.livedata.User;
import com.dpearth.dvox.models.fragments.AddFragment;
import com.dpearth.dvox.models.fragments.HomeFragment;
import com.dpearth.dvox.models.fragments.UserFragment;
import com.dpearth.dvox.smartcontract.Post;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.dpearth.dvox.username.Username;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Random;

>>>>>>> Stashed changes
public class MainActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

<<<<<<< Updated upstream
    @Override
    protected void onCreate(Bundle savedInstanceState) {
=======
    private PostViewModel postViewModel;


    @Override
    protected void onCreate (Bundle savedInstanceState){


        //ViewMOdelProviders.of(this) ... is no longer supported >:-(
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getAllPosts().observe(this, new Observer<List<Post>>() {

            //This will get triggered everytime live data changes
            @Override
            public void onChanged(List<Post> posts) {
                //!!!update RecycleView!!!!!!!!!
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        //Get shared preferences and put new APIs there
        SharedPreferences preferencesKeys = getSharedPreferences("pref", Context.MODE_PRIVATE);
        getAPIs(preferencesKeys);

        SharedPreferences preferencesUsernames = getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);

        Username usernameInstance = new Username(this);
        usernameInstance.retrieveUsername(true);

>>>>>>> Stashed changes
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
                        //Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ic_home:
                        fragment = new HomeFragment();
                        //Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ic_user:
                        //String a = getRandomlyGeneratedName();    //Testing Randomly generated names
                        //Toast.makeText(MainActivity.this, a, Toast.LENGTH_SHORT).show();
                        fragment = new UserFragment();
                        break;
                    default:
                        fragment = new HomeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.ic_home);
    }




}