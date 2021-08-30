package com.dpearth.dvox.livedata;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dpearth.dvox.daos.PostDao;
import com.dpearth.dvox.smartcontract.Post;

//when we add comments to database, we go like this {Post.class} and something else. look up...
//versions of sql. When in production and we update database structure. we increment it

@Database(entities = {Post.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {

    private static PostDatabase instance;   //we have one instance of database

    public abstract PostDao postDao();  //Access database opertaion methods

    //synchronized - only one thread can access this method. so that there are not two instances of database
    public static synchronized PostDatabase getInstance(Context context){
        // If not instanciated, we do it. Else return existing one
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PostDatabase.class, "post_database")
                    .fallbackToDestructiveMigration()   //To prevent illegal state exception when increment version
                                                        //  to better migrate on a new version.
                                                        //  delete database & create new from scratch
                    .build();
        }
        return instance;
    }

    //Populate database oncreate
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private PostDao postDao;

        public PopulateDbAsyncTask(PostDatabase db) {
            this.postDao = db.postDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
