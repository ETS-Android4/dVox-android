package com.dpearth.dvox;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomNameGenerator {

    private static ArrayList<String> alreadyUsedName = new ArrayList<>();   //We can change it later into HashMapping

    private final static String[] adjectives = new String[]{
        "Sturdy",
        "Loud",
        "Delicious",
        "Decorous",
        "Pricey",
        "Knowing",
        "Scientific",
        "Lazy",
        "Fair",
        "Loutish",
        "Wonderful",
        "Strict",
        "Gaudy",
        "Innocent",
        "Horrible",
        "Puzzled",
        "Happy",
        "Grandiose",
        "Observant",
        "Pumped",
        "Pale",
        "Royal",
        "Flawless",
        "Actual",
        "Realistic",
        "Cynical",
        "Clean",
        "Strict",
        "Super",
        "Powerful",
        "Mixed",
        "Slim",
        "Ubiquitous",
        "Faithful",
        "Amusing",
        "Emotional",
        "Staking",
        "Former",
        "Scarce",
        "Tense",
        "Black-and-White",
        "Tangy",
        "Wrong",
        "Sloppy",
        "Regular",
        "Deafening",
        "Savory",
        "Classy",
        "First",
        "Second",
        "Third",
        "Valuable",
        "Outgoing",
        "Free",
        "Terrific",
        "Sleepy",
        "Adorable",
        "Cozy"
    };

    private final static String[] animals = new String[]{
        "Boar",
        "Koala",
        "Snake",
        "Frog",
        "Parrot",
        "Lion",
        "Pig",
        "Rhino",
        "Sloth",
        "Horse",
        "Sheep",
        "Chameleon",
        "Giraffe",
        "Yak",
        "Cat",
        "Dog",
        "Penguin",
        "Elephant",
        "Fox",
        "Otter",
        "Gorilla",
        "Rabbit",
        "Raccoon",
        "Wolf",
        "Panda",
        "Goat",
        "Chicken",
        "Duck",
        "Cow",
        "Ray",
        "Catfish",
        "Ladybug",
        "Dragonfly",
        "Owl",
        "Beaver",
        "Alpaca",
        "Mouse",
        "Walrus",
        "Kangaroo",
        "Butterfly",
        "Jellyfish",
        "Deer",
        "Beetle",
        "Tiger",
        "Pigeon",
        "Bearded_Dragon",
        "Bat",
        "Hippo",
        "Crocodile",
        "Monkey",
    };

    private static String[] numbers = new String[100];


    private String adjective;
    private String animal;
    private File image;

    public RandomNameGenerator() {
    }


    public static String getRandomlyGeneratedName() {

        for (int i = 0; i < 100; i++){
            numbers[i] = String.valueOf(i);
        }

        Random random = new Random();
        int randAdj = random.nextInt(adjectives.length);
        int randAnimal = random.nextInt(animals.length);
        int randNumber = random.nextInt(numbers.length);

//        String adjective = adjectives[randAdj];
//        String animal = animals[randAnimal];
//        String number = numbers[randNumber];

        //For Testing Purposes
        String adjective = "Royal";
        String animal = "Butterfly";
        String number = "69";

        String generateName = "@" + adjective + "_" + animal + "_" + number;

        DocumentReference firebaseAnimal = FirebaseFirestore.getInstance().collection("Nicknames").document(animal);


//        checkAndAddGeneratedNameFireStore(firebaseAnimal, generateName, false);
//        addGeneratedNameFireStore(firebaseAnimal, generateName, true);
//        deleteNameFromFireStore(firebaseAnimal, generateName);

        return generateName;

    }

    /**
     * Deleting name from FireStore
     *
     * @param firebaseAnimal
     * @param generatedName
     */
    public static void deleteNameFromFireStore(DocumentReference firebaseAnimal, String generatedName){

        String nameToSplit = generatedName.substring(1);

        String[] a = nameToSplit.split("_", 3);

        String adjective = a[0];
        String animal = a[1];
        String number = a[2];

        Map<String, Object> animalToDelete = new HashMap<>();

        animalToDelete.put(adjective + "_" + number, FieldValue.delete());

        firebaseAnimal.update(animalToDelete).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                    Log.d("RNG Delete", "Successfully deleted generatedName: " + generatedName);
            }
        });


    }

    /**     Maybe this one was not necessary
     * Checking if name exists in firebase and adding afterwards
     *
     * @param firebaseAnimal
     * @param generatedName
     * @param isNameUsed
     */
    private static void checkAndAddGeneratedNameFireStore(DocumentReference firebaseAnimal, String generatedName, boolean isNameUsed){

        String nameToSplit = generatedName.substring(1);

        String[] a = nameToSplit.split("_", 3);

        String adjective = a[0];
        String animal = a[1];
        String number = a[2];

        firebaseAnimal.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){

                    DocumentSnapshot document = task.getResult();

                    //Checking if animal exists
                    if (document.exists()){

                        //Checking if Adjective & number combination exists
                        if (document.getBoolean(adjective + "_" + number) != null){
                            Log.d("firebase RNG", generatedName + " exists");
                        } else {
                            Log.d("firebase RNG", generatedName + " DOES NOT exist");

                            addGeneratedNameFireStore(firebaseAnimal, generatedName, isNameUsed);
                        }


                    } else {
                        Log.d("firebase RNG", "animal " + animal + " DOES NOT exist");
                    }

                } else {
                    Log.d("firebase RNG", "Task Failed with: " + task.getException());
                }
            }
        });
    }


    /**
     *  Adding generated name to Firebase
     *
     * @param firebaseAnimal
     * @param generatedName
     * @param isNameUsed
     */
    private static void addGeneratedNameFireStore(DocumentReference firebaseAnimal, String generatedName, boolean isNameUsed) {

        String nameToSplit = generatedName.substring(1);

        String[] a = nameToSplit.split("_", 3);

        String adjective = a[0];
        String animal = a[1];
        String number = a[2];

        Map<String, Boolean> animalToAdd = new HashMap<>();
        animalToAdd.put(adjective + "_" + number, isNameUsed);

        firebaseAnimal.set(animalToAdd, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("firebase RNG", "animal " + generatedName + " successfully written!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("firebase RNG", "Error writing " + generatedName, e);
            }
        });
    }
}
