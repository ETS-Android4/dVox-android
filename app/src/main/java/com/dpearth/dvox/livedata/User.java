package com.dpearth.dvox.livedata;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.dpearth.dvox.BR;
import com.dpearth.dvox.R;

public class User  extends BaseObservable {

    private String name;
    private int image = R.drawable.snake;

    public User(String name) {
        this.name = name;
        image = R.drawable.snake;
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
}