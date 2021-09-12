package com.dpearth.dvox.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dpearth.dvox.smartcontract.Post;

import java.util.List;

public class PostViewModel extends AndroidViewModel {

    private PostRepository repository;
    private LiveData<List<Post>> allPosts;

    //We use application whenever context is needed
    //Never store referance to context in view model cuz its supposed to outlive it
    // so don't store already destroyed context
    public PostViewModel(@NonNull Application application) {
        super(application);
        repository = new PostRepository(application);
        allPosts = repository.getAllPosts();
    }

    public void insert(Post post) {
        repository.insert(post);
    }

    public void update(Post post) {
        repository.update(post);
    }

    public void delete(Post post) {
        repository.delete(post);
    }

    public void deleteAllPosts() {
        repository.deleteAllPosts();
    }

    public int getCount(){
        return repository.getPostCount();
    }

    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }
}
