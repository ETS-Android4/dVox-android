package com.dpearth.dvox;

import static com.dpearth.dvox.RandomNameGenerator.getRandomlyGeneratedName;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muddzdev.styleabletoast.StyleableToast;

public class LoginActivity extends AppCompatActivity {

    public static final String ANDROID_PACKAGE = "com.dpearth.dvox";
    public static final String IOS_PACKAGE = "com.dpearth.dvox";
    public static final String FIREBASE_LINK = "https://dvox.page.link/sign";

    public static final String LOGIN_TAG = "LoginActivity_projectDIES" ; //A string for debug purposes

    private EditText emailInput;
    private Button verifyButton;

    private FirebaseFirestore fstore;
    private String userID;


    private static final String anonymousUserName = getRandomlyGeneratedName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Checking to see if phone is rooted
        if (isRootGiven()){
            super.onCreate(savedInstanceState);
            setTheme(R.style.LoginTheme_ProjectDIES);
            setContentView(R.layout.phone_is_rooted);
            Log.d("Rooted Phone", "onCreate: Phone is rooted. Can't access the app");
        } else {

            //Create login activity
            super.onCreate(savedInstanceState);
            setTheme(R.style.LoginTheme_ProjectDIES);
            setContentView(R.layout.activity_login);

            //Assign buttons
            emailInput = findViewById(R.id.email_input);
            verifyButton = findViewById(R.id.createButton);

            //Need to instantiate fStore
            fstore = FirebaseFirestore.getInstance();

            //Verify button method
            verifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkEmailInput()) {
                        googleLogin(emailInput.getText().toString());
                        saveEmail(emailInput.getText().toString());
                    } else {
                        shake();
                    }
                }
            });

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                switchToMainActivity();
            }

            FirebaseDynamicLinks.getInstance()
                    .getDynamicLink(getIntent())
                    .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                        @Override
                        public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                            // Get deep link from result (may be null if no link is found)
                            Uri deepLink = null;
                            if (pendingDynamicLinkData != null) {
                                deepLink = pendingDynamicLinkData.getLink();
                            }

                            FirebaseAuth auth = FirebaseAuth.getInstance();
                            Intent intent = getIntent();

                            String emailLink = "";
                            if (deepLink != null)
                                try {
                                    emailLink = deepLink.toString();
                                } catch (Exception error) {
                                    StyleableToast.makeText(LoginActivity.this, "Error. Request a new link.", Toast.LENGTH_LONG, R.style.LoginToast).show();
                                }
                            if (auth.isSignInWithEmailLink(emailLink)) {
                                // Retrieve this from wherever you stored it
                                SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
                                String email = sharedPreferences.getString("email", "defaultValue");
                                // The client SDK will parse the code from the link for you.
                                auth.signInWithEmailLink(email, emailLink)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @SuppressLint("LongLogTag")
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(LOGIN_TAG, "Successfully signed in with email link!");
                                                    StyleableToast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG, R.style.LoginToast).show();

                                                    // You can access the new user via result.getUser()
                                                    // Additional user info profile *not* available via:
                                                    // result.getAdditionalUserInfo().getProfile() == null
                                                    // You can check if the user is new or existing:
                                                    // result.getAdditionalUserInfo().isNewUser()
                                                    switchToMainActivity();
                                                    AuthResult result = task.getResult();
                                                } else {
                                                    Log.e(LOGIN_TAG, "Error signing in with email link", task.getException());
                                                    StyleableToast.makeText(LoginActivity.this, "Error. Request a new link.", Toast.LENGTH_LONG, R.style.LoginToast).show();
                                                }
                                            }
                                        });
                            }


                            // Handle the deep link. For example, open the linked
                            // content, or apply promotional credit to the user's
                            // account.
                            // ...

                            // ...
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @SuppressLint("LongLogTag")
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(LOGIN_TAG, "getDynamicLink:onFailure", e);
                        }
                    });

        }
    }

    private void switchToMainActivity() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }

    @SuppressLint("LongLogTag")
    private void googleLogin(String email){
        ActionCodeSettings actionCodeSettings =
                ActionCodeSettings.newBuilder()
                        // URL you want to redirect back to. The domain (www.example.com) for this
                        // URL must be whitelisted in the Firebase Console.
                        .setUrl("https://projectdies-55a14.firebaseapp.com/__/auth/action?mode=action&oobCode=code")
                        // This must be true
                        .setHandleCodeInApp(true)
                        .setIOSBundleId(IOS_PACKAGE)
                        .setAndroidPackageName(
                                ANDROID_PACKAGE,
                                true, /* installIfNotAvailable */
                                "12"    /* minimumVersion */)
                        .build();
        Log.d(LOGIN_TAG, FIREBASE_LINK + "finishSignUp?cartId=1234");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(email, actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(LOGIN_TAG, "Email sent.");
                            StyleableToast.makeText(LoginActivity.this, "The link was sent!", Toast.LENGTH_LONG, R.style.LoginToast).show();
                        }
                        else {
                            Log.d(LOGIN_TAG, "Failed. Error: " + task.getException());
                        }
                    }
                });
    }
    private boolean checkEmailInput(){

        if (emailInput.getText().toString() == "dvox-test-email@yandex.com"){
            return true;
        }

        String emailPattern =
                "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+(edu)";
        Pattern pat = Pattern.compile(emailPattern);
        if (emailInput.getText().toString().isEmpty()) {
            StyleableToast.makeText(LoginActivity.this, "The field cannot be empty", Toast.LENGTH_LONG, R.style.LoginToast).show();
            return false;
        }
        if (pat.matcher(emailInput.getText().toString()).matches() == false){
            StyleableToast.makeText(LoginActivity.this, "Please use a valid college (.edu) email", Toast.LENGTH_LONG, R.style.LoginToast).show();
            return false;
        }
        return true;
    }

    private void saveEmail(String email){
        // Get shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        // Create an editor for shared preferences
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        // Put email
        myEdit.putString("email", email);
        // Commit changes
        myEdit.commit();
    }

    private void shake(){
        YoYo.with(Techniques.Shake).playOn(findViewById(R.id.email_input));
    }

    public static boolean isRootGiven(){
        if (isRootAvailable()) {
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(new String[]{"su", "-c", "id"});
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = in.readLine();
                if (output != null && output.toLowerCase().contains("uid=0"))
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (process != null)
                    process.destroy();
            }
        }

        return false;
    }

    public static boolean isRootAvailable(){
        for(String pathDir : System.getenv("PATH").split(":")){
            if(new File(pathDir, "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
