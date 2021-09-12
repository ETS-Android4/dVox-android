package com.dpearth.dvox.livedata;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.dpearth.dvox.daos.PostDao;
import com.dpearth.dvox.smartcontract.Post;

import java.util.List;

//API ~ idk if its correct word. to access data from database or from internet (Not in this case)
public class PostRepository {

    private PostDao postDao;
    private LiveData<List<Post>> allPosts;

    public PostRepository(Application application) {
        PostDatabase database = PostDatabase.getInstance(application);
        postDao = database.postDao();   //Code is generated for this method by database
        allPosts = postDao.getAllPosts();
    }

    //Room does not allow database operations on main thread because app will freeze -> crash

    //API that repository exposes outside
    public void insert(Post post) {
        new InsertPostAsyncTask(postDao).execute(post);
    }

    public void update(Post post) {
        new UpdatePostAsyncTask(postDao).execute(post);
    }

    public void delete(Post post) {
        new DeletePostAsyncTask(postDao).execute(post);
    }

    public void deleteAllPosts() {
        new DeleteAllPostsPostAsyncTask(postDao).execute();
    }

    public int getPostCount(){
        return  postDao.getCount();
    }

    public LiveData<List<Post>> getAllPosts() {
        return allPosts;
    }

    //Static so that it has no referacne with repository otherwise memory will leak
    private static class InsertPostAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao postDao;

        public InsertPostAsyncTask(PostDao postDao) {
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDao.insert(posts[0]);
            return null;
        }
    }

    //Creating async tasks
    private static class UpdatePostAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao postDao;

        public UpdatePostAsyncTask(PostDao postDao) {
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDao.update(posts[0]);
            return null;
        }
    }

    private static class DeletePostAsyncTask extends AsyncTask<Post, Void, Void> {
        private PostDao postDao;

        public DeletePostAsyncTask(PostDao postDao) {
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Post... posts) {
            postDao.delete(posts[0]);
            return null;
        }
    }

    private static class DeleteAllPostsPostAsyncTask extends AsyncTask<Void, Void, Void> {
        private PostDao postDao;

        public DeleteAllPostsPostAsyncTask(PostDao postDao) {
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            postDao.deleteAllPosts();
            return null;
        }
    }
}
