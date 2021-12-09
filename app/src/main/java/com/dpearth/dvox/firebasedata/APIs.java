package com.dpearth.dvox.firebasedata;


import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Gets all API keys from Firestore Database.
 *
 * !!!  FOR SECURITY REASONS SHOULD BE HIDDEN WHEN THE SOURCE CODE IS OPENED
 */
public class APIs {

    public static final String TAG = "APIs_Tag";

    /**
     * Initializer that retrieves all APIs and puts them into the private shared storage
     * accessible from any other class.
     *
     * @param preferences - SharedPreferences object
     */
    public APIs(SharedPreferences preferences) {

        setOnError(preferences);

        //Get the reference to the Firestore API document
        DocumentReference Doc = FirebaseFirestore.getInstance().collection("APIs").document("7rMOmCufceCpoXgxLRKo");

        Doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            /**
             * Executes when the document is received.
             */
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    //Getting results of the document
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        // Retrieving specific APIs
                        String Credentials = document.getString("credentials");
                        String ContractAddress = document.getString("contractAddress");
                        String InfuraURL = document.getString("infuraURL");

                        setOnSuccess(preferences, Credentials, ContractAddress, InfuraURL);

                    } else {
                        // On error
                        Log.d(TAG, "Check the name of the document");

                        setOnError(preferences);
                    }
                } else {
                    //On error
                    Log.d(TAG, "get failed with ", task.getException());

                    setOnError(preferences);
                }
            }
        });
    }

    /**
     * Function to execute if the data is retrieved successfully.
     *
     * @param preferences - SharedPreferences object
     * @param Credentials - Credeintials to put
     * @param ContractAddress - Contract Address to put
     * @param InfuraURL - Infura URL to put
     */
    private void setOnSuccess(SharedPreferences preferences, String Credentials, String ContractAddress, String InfuraURL){
        SharedPreferences.Editor prefsEditor = preferences.edit();

        prefsEditor.putString("contractAddress", "0x47D3e11D792d7b4B808775629Fcd5A36CfAf00E6");
        prefsEditor.putString("credentials", Credentials);
        prefsEditor.putString("infuraURL", InfuraURL);

        prefsEditor.commit();
    }

    /**
     * Function to execute if the data is retrieved with error (unsuccessfully).
     *
     * @param preferences - SharedPreferences object
     */
    private void setOnError(SharedPreferences preferences){
        SharedPreferences.Editor prefsEditor = preferences.edit();

        prefsEditor.putString("credentials", "error");
        prefsEditor.putString("contractAddress", "error");
        prefsEditor.putString("infuraURL", "error");

        prefsEditor.commit();
    }
}

