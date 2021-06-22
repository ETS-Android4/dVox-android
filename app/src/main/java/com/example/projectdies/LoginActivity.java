package com.example.projectdies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity" ; //A string for debug purposes

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
            }
        });
    }

    private void switchToMainActivity() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
}
