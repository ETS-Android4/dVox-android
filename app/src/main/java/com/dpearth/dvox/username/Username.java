package com.dpearth.dvox.username;

import android.database.Observable;
import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.dpearth.dvox.R;

import java.util.HashMap;
import java.util.Map;

public class Username {

    private ObservableField<String> username;
    private ObservableInt image;
    private ObservableBoolean isUsed;

    private static Map<Integer, String> map = new HashMap<>();


    public Username(String username, boolean isUsed) {
        this.username.set(username);
        this.isUsed.set(isUsed);
    }

    public Username() {
    }

    private void populateMap() {
        map.put(1, "Boar");
        map.put(2, "Koala");
        map.put(3, "Snake");
        map.put(4, "Frog");
        map.put(5, "Parrot");
        map.put(6, "Lion");
        map.put(7, "Pig");
        map.put(8, "Rhino");
        map.put(9, "Sloth");
        map.put(10, "Horse");
        map.put(11, "Sheep");
        map.put(12, "Chameleon");
        map.put(13, "Giraffe");
        map.put(14, "Yak");
        map.put(15, "Cat");
        map.put(16, "Dog");
        map.put(17, "Penguin");
        map.put(18, "Elephant");
        map.put(19, "Fox");
        map.put(20, "Otter");
        map.put(21, "Gorilla");
        map.put(22, "Rabbit");
        map.put(23, "Raccoon");
        map.put(24, "Wolf");
        map.put(25, "Panda");
        map.put(26, "Goat");
        map.put(27, "Chicken");
        map.put(28, "Duck");
        map.put(29, "Cow");
        map.put(30, "Ray");
        map.put(31, "Catfish");
        map.put(32, "Ladybug");
        map.put(33, "Dragonfly");
        map.put(34, "Owl");
        map.put(35, "Beaver");
        map.put(36, "Alpaca");
        map.put(37, "Mouse");
        map.put(38, "Walrus");
        map.put(39, "Kangaroo");
        map.put(40, "Butterfly");
        map.put(41, "Jellyfish");
        map.put(42, "Deer");
        map.put(43, "Beetle");
        map.put(44, "Tiger");
        map.put(45, "Pigeon");
        map.put(46, "Bearded_dragon");
        map.put(47, "Bat");
        map.put(48, "Hippo");
        map.put(49, "Crocodile");
        map.put(50, "Monkey,");
    }

    public String getUsername() {
        return username.get();
    }
}
