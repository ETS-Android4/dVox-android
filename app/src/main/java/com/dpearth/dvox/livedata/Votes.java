package com.dpearth.dvox.livedata;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Votes extends BaseObservable {

    private int upvotes;
    private int downvotes;
    private long postId;

    private DocumentReference firesStore;

    public Votes(long postId) {
        this.postId = postId;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    @Bindable
    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    @Bindable
    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String fieldString(boolean upvote) {
        if (upvote) {
            return getPostId() + "_upvote";
        } else {
            return getPostId() + "_downvote";
        }
    }

    public void getVotesFireStore(boolean isUpvote) {

        double[] numUpvotes = new double[1];
        String fieldString = fieldString(isUpvote);

        getFireStore().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    numUpvotes[0] = documentSnapshot.getLong(fieldString);
                    Log.d("..i..", "# of votes = " + numUpvotes[0]);

                    if (isUpvote) {
                        upvotes = (int) numUpvotes[0];
                    } else {
                        downvotes = (int) numUpvotes[0];
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("..i..", "");
            }
        });

//        if (isUpvote) {
//            upvotes = (int) numUpvotes[0];
//        } else {
//            downvotes = (int) numUpvotes[0];
//        }
    }

    private DocumentReference getFireStore() {
        firesStore = FirebaseFirestore.getInstance().collection("Votes")
                .document("0x47D3e11D792d7b4B808775629Fcd5A36CfAf00E6");

        return firesStore;
    }

    @Override
    public String toString() {
        return "Votes{" +
                "upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", postId=" + postId +
                '}';
    }
}
