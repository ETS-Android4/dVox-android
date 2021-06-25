package com.example.projectdies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public static final String ANDROID_PACKAGE = "com.example.projectdies";
    public static final String IOS_PACKAGE = "com.example.ios";
    public static final String FIREBASE_LINK = "https://projectdies-55a14.firebaseapp.com/";

    public static final String TAG = "LoginActivity_projectDIES" ; //A string for debug purposes

    private EditText emailInput;
    private Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create login activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Assign buttons
        emailInput = findViewById(R.id.email_input);
        verifyButton = findViewById(R.id.verify_button);

        //Verify button method
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Verify button is clicked", Toast.LENGTH_SHORT).show();
                switchToMainActivity();
                googleLogin();
            }
        });
    }

    private void switchToMainActivity() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }

    private void googleLogin(){
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
        Log.d(TAG, FIREBASE_LINK + "finishSignUp?cartId=1234");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendSignInLinkToEmail(emailInput.getText().toString(), actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                        else {
                            Log.d(TAG, "Failed. Error: " + task.getException());
                        }
                    }
                });
    }
}
