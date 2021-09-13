package com.dpearth.dvox.livedata;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import jnr.ffi.annotations.In;

public class VotesDictionary extends Observable {
    Map<String,String> map;
    Context context;

    public VotesDictionary(Context context){
        this.context = context;
        map = new HashMap<>();
        SharedPreferences pSharedPref = context.getSharedPreferences("VotesContainer", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("votesMap", (new JSONObject()).toString());
                if (jsonString != null) {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    Iterator<String> keysItr = jsonObject.keys();
                    while (keysItr.hasNext()) {
                        String key = keysItr.next();
                        String value = jsonObject.getString(key);
                        map.put(key, value);
                    }
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void addVote(int postId, int vote){
        map.put(String.valueOf(postId), String.valueOf(vote));
        setChanged();
        notifyObservers();
        SharedPreferences pSharedPref = context.getSharedPreferences("VotesContainer", Context.MODE_PRIVATE);
        JSONObject jsonObject = new JSONObject(map);
        String jsonString = jsonObject.toString();
        pSharedPref.edit()
                .remove("votesMap")
                .putString("votesMap", jsonString)
                .apply();

    }

    public int getVote(int postId){
        try{
            return Integer.valueOf(map.get(String.valueOf(postId)));
        } catch (Exception e){
            return 0;
        }
    }
}
