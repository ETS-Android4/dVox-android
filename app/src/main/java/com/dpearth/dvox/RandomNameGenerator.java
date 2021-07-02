package com.dpearth.dvox;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
        "Homeless",
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
        "Black-and-white",
        "Tangy",
        "Wrong",
        "Sloppy",
        "Regular",
        "Deafening",
        "Savory",
        "Sturdy",
        "Classy",
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
        "Bearded_dragon",
        "Bat",
        "Hippo",
        "Crocodile",
        "Monkey",
    };

    private static HashMap<String, String> animalImages = new HashMap<>();


    private String adjective;
    private String animal;
    private File image;

    public RandomNameGenerator() {
    }

    private static void animalImagesInitializer(){
        animalImages.put("Boar", "");
        animalImages.put("Koala", "");
        animalImages.put("Snake", "");
        animalImages.put("Frog", "");
        animalImages.put("Parrot", "");
        animalImages.put("Lion", "");
        animalImages.put("Pig", "");
        animalImages.put("Rhino", "");
        animalImages.put("Sloth", "");
        animalImages.put("Horse", "");
        animalImages.put("Sheep", "");
        animalImages.put("Chameleon", "");
        animalImages.put("Giraffe", "");
        animalImages.put("Yak", "");
        animalImages.put("Cat", "");
        animalImages.put("Dog", "");
        animalImages.put("Penguin", "");
        animalImages.put("Elephant", "");
        animalImages.put("Fox", "");
        animalImages.put("Otter", "");
        animalImages.put("Gorilla", "");
        animalImages.put("Rabbit", "");
        animalImages.put("Raccoon", "");
        animalImages.put("Wolf", "");
        animalImages.put("Panda", "");
        animalImages.put("Goat", "");
        animalImages.put("Chicken", "");
        animalImages.put("Duck", "");
        animalImages.put("Cow", "");
        animalImages.put("Ray", "");
        animalImages.put("Catfish", "");
        animalImages.put("Ladybug", "");
        animalImages.put("Dragonfly", "");
        animalImages.put("Owl", "");
        animalImages.put("Beaver", "");
        animalImages.put("Alpaca", "");
        animalImages.put("Mouse", "");
        animalImages.put("Walrus", "");
        animalImages.put("Kangaroo", "");
        animalImages.put("Butterfly", "");
        animalImages.put("Jellyfish", "");
        animalImages.put("Deer", "");
        animalImages.put("Beetle", "");
        animalImages.put("Tiger", "");
        animalImages.put("Pigeon", "");
        animalImages.put("Bearded_dragon", "");
        animalImages.put("Bat", "");
        animalImages.put("Hippo", "");
        animalImages.put("Crocodile", "");
        animalImages.put("Monkey,", "");
    }

    public static String getRandomlyGeneratedName() {

        Random random = new Random();
        int randAdj = random.nextInt(adjectives.length);
        int randAnimal = random.nextInt(animals.length);

        String part1 = adjectives[randAdj];
        String part2 = animals[randAnimal];
        String generateName = part1 + " " + part2;


        //Needs to be changed! add numbers (While loop)
        if (alreadyUsedName.contains(generateName)){
            return getRandomlyGeneratedName();
        } else {
            alreadyUsedName.add(generateName);
            return generateName;
        }

    }


}
