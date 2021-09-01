package com.dpearth.dvox.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dpearth.dvox.smartcontract.Post;

import java.util.List;

@Dao
public interface PostDao {  //Dao - Data access object

    @Insert
    void insert(Post post);//Adding to database. I guess

    @Update
    void update(Post post);

    @Delete
    void delete(Post post);

    @Query("DELETE FROM post_table")
    void deleteAllPosts();  //Does not have delete all, so we use @Query notation

    //This may change in 2 ways:
    // 1 add priotiry varuable 2 dont auto generate id
    @Query("SELECT * FROM post_table ORDER BY id DESC")
    LiveData<List<Post>> getAllPosts();   //Compilter checks if post_table content is compatable with List<Post>
    //List of posts are livedata
}
