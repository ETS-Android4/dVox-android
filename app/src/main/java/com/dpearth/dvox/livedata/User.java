package com.dpearth.dvox.livedata;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.dpearth.dvox.BR;
import com.dpearth.dvox.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class User  extends BaseObservable {

    private String name;
    private int image;
    private Map<String, Integer> map;

    public User(String name) {
        this.map = new HashMap<>();
        populateMap();
        this.name = name;


        String[] splitName = name.split("_");
        String animal = splitName[1];


        if (splitName.length == 3){
            animal = splitName[1];
        } else if (splitName.length == 4){
            animal = splitName[1] + "_" + splitName[2];
        } else {
            animal = "Hacker";
        }

        image = map.get(animal.toLowerCase());

    }

    @Bindable
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }
    
    private void populateMap() {
        map.put("boar", R.drawable.boar);
        map.put("koala", R.drawable.koala);
        map.put("snake", R.drawable.snake);
        map.put("frog", R.drawable.frog);
        map.put("parrot", R.drawable.parrot);
        map.put("lion", R.drawable.lion);
        map.put("pig", R.drawable.pig);
        map.put("rhino", R.drawable.rhino);
        map.put("sloth", R.drawable.sloth);
        map.put("horse", R.drawable.horse);
        map.put("sheep", R.drawable.sheep);
        map.put("chameleon", R.drawable.chameleon);
        map.put("giraffe", R.drawable.giraffe);
        map.put("yak", R.drawable.yak);
        map.put("cat", R.drawable.cat);
        map.put("dog", R.drawable.dog);
        map.put("penguin", R.drawable.penguin);
        map.put("elephant", R.drawable.elephant);
        map.put("fox", R.drawable.fox);
        map.put("otter", R.drawable.otter);
        map.put("gorilla", R.drawable.gorilla);
        map.put("rabbit", R.drawable.rabbit);
        map.put("raccoon", R.drawable.raccoon);
        map.put("wolf", R.drawable.wolf);
        map.put("panda", R.drawable.panda);
        map.put("goat", R.drawable.goat);
        map.put("chicken", R.drawable.chicken);
        map.put("duck", R.drawable.duck);
        map.put("cow", R.drawable.cow);
        map.put("ray", R.drawable.ray);
        map.put("catfish", R.drawable.catfish);
        map.put("ladybug", R.drawable.ladybug);
        map.put("dragonfly", R.drawable.dragonfly);
        map.put("owl", R.drawable.owl);
        map.put("beaver", R.drawable.beaver);
        map.put("alpaca", R.drawable.alpaca);
        map.put("mouse", R.drawable.mouse);
        map.put("walrus", R.drawable.walrus);
        map.put("kangaroo", R.drawable.kangaroo);
        map.put("butterfly", R.drawable.butterfly);
        map.put("jellyfish", R.drawable.jellyfish);
        map.put("deer", R.drawable.deer);
        map.put("beetle", R.drawable.beetle);
        map.put("tiger", R.drawable.tiger);
        map.put("pigeon", R.drawable.pigeon);
        map.put("bearded_dragon", R.drawable.bearded_dragon);
        map.put("bat", R.drawable.bat);
        map.put("hippo", R.drawable.hippo);
        map.put("crocodile", R.drawable.crocodile);
        map.put("monkey", R.drawable.monkey);
        map.put("hacker", R.drawable.hacker);
    }
}