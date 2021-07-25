package com.dpearth.dvox;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.InputType;
import android.view.InputDevice;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dpearth.dvox.models.fragments.AddFragment;
import com.dpearth.dvox.models.fragments.HomeFragment;
import com.dpearth.dvox.models.fragments.UserFragment;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.muddzdev.styleabletoast.StyleableToast;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class MainActivity extends AppCompatActivity {

    //Change Location
    //Connect to Network
    private static String contractAddress = "";
    private static String infuraURL = "";
    private static Credentials credentials = Credentials.create("");
    private static SmartContract smartContract = new SmartContract(contractAddress, infuraURL, credentials);


    private EditText postTitle, postTheme, postContent;
    private Button buttonSave;


    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate (Bundle savedInstanceState){
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

    //Maybe this belongs in AddFragment class
    public void startThread(View view){
        ExampleThread thread = new ExampleThread(100);
        thread.start();
    }

    public void stopThread(View view){

    }

    class ExampleThread extends Thread {
        int seconds;

        ExampleThread(int seconds){
            this.seconds = seconds;
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


//                        for (int i = 0; i < seconds; i++) {
//                            System.out.println("\n\ttitle: " + title + "\ntheme: " +
//                                    theme + "\ncontent: " + content + "\n counter: " + i);

                            long startTime = System.nanoTime();
                            //smartContract.addVote(4, 1);
                            //smartContract.createPost(title, "Revaz", theme, content);
                            String a = smartContract.getPost(7).toString();
                            long endTime = System.nanoTime();
                            long totalTime = NANOSECONDS.toMillis(endTime - startTime);

                            System.out.println("POST: \n\t" + a
                            + "\nTime for addvote: " + totalTime + " ms");


//                            try {
//                                //Sleep for testing Thread
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }



            //<-- Create POST -->\\
            //smartContract.createPost(title, "Revaz", content, theme);
            //smartContract.addVote(2, 1);

            //System.out.println("POST MESSAGE: " + smartContract.getPost(4).toString());




            //smartContract = new SmartContract(contractAddress, infuraURL, credentials);

/*
            //Testing Thread
            for (int i = 0; i < seconds; i++) {

                System.out.println("Testing Thread \nThread #" + i);
                try {
                    //Sleep for testing Thread
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

 */




        }
    }

}