package com.dpearth.dvox.livedata;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Observable;

public class Votes extends Observable {

    private String firebaseDocument = "0x47D3e11D792d7b4B808775629Fcd5A36CfAf00E6";

    private long postId;

    public int upvotes;
    public int downvotes;
    private String fieldUp;
    private String fieldDown;

    public Votes(long postId) {
        this.postId = postId;
        this.upvotes = 0;
        this.downvotes = 0;
        fieldUp = String.valueOf(postId) + "_upvote";
        fieldDown = String.valueOf(postId) + "_downvote";
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setVotes() {

        //Get the reference to the Firestore API document
        DocumentReference Doc = FirebaseFirestore.getInstance().collection("Votes").document(firebaseDocument);

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
                        try {
                            upvotes = document.getLong(fieldUp).intValue();
                            Log.d("UpVotesGetter", String.valueOf(upvotes));
                            setChanged();
                            notifyObservers();

                        } catch (Exception error) {
                            Log.d("UpVotesGetter", error.getLocalizedMessage());
                        }

                        try {
                            downvotes = document.getLong(fieldDown).intValue();
                            Log.d("DownVotesGetter", String.valueOf(downvotes));
                            setChanged();
                            notifyObservers();

                        } catch (Exception error) {
                            Log.d("DownVotesGetter", error.getLocalizedMessage());
                        }

                    }
                }
            }
        });
    }

    public void upVote(long vote) {

        //Get the reference to the Firestore API document
        DocumentReference Doc = FirebaseFirestore.getInstance().collection("Votes").document(firebaseDocument);

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
                        try {
                            Doc.update(fieldUp, FieldValue.increment(vote));
                            upvotes = upvotes + (int) vote;
                            Log.d("UpVotesGetter", String.valueOf(upvotes));
                            setChanged();
                            notifyObservers();

                        } catch (Exception error) {
                            Log.d("UpVotesGetter", error.getLocalizedMessage());
                        }
                    }
                }
            }
        });
    }

    public void downVote(long vote) {

        //Get the reference to the Firestore API document
        DocumentReference Doc = FirebaseFirestore.getInstance().collection("Votes").document(firebaseDocument);

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

                        try {
                            Doc.update(fieldDown, FieldValue.increment(vote));
                            downvotes = downvotes + (int) vote;
                            Log.d("DownVotesGetter", String.valueOf(downvotes));
                            setChanged();
                            notifyObservers();

                        } catch (Exception error) {
                            Log.d("DownVotesGetter", error.getLocalizedMessage());
                        }

                    }
                }
            }
        });
    }
}
