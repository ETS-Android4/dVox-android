package com.dpearth.dvox.smartcontract;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jnr.ffi.mapper.DataConverter;

/**
 *
 *  A simple post class.
 *
 *  @author Aleksandr Molchagin
 *  @version 06.27.2021
 */

@Entity(tableName = "post_table")
public class Post {

    @PrimaryKey(autoGenerate = true)//We might change that into false because we already have ids
    private long id;

    private String title;
    private String author;
    private String message;
    private String hashtag;

    private int commentCount;
    private boolean ban;

    //////////////////
    /* Constructor for @Entity */
    //////////////////
    @Ignore
    public Post(String title, String author, String message, String hashtag) {

        this.id = id;

        this.title = title;
        this.author = author;
        this.message = message;
        this.hashtag = hashtag;

        this.commentCount = 0;
        this.ban = false;

    }

    //////////////////
    /* Constructors */
    //////////////////

    public Post(){
        this(0, null, null, null, null);
    }

    @Ignore
    public Post(long id, String title, String author, String message, String hashtag) {

        this.id = id;

        this.title = title;
        this.author = author;
        this.message = message;
        this.hashtag = hashtag;

        this.commentCount = 0;
        this.ban = false;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", commentCount=" + commentCount +
                ", ban=" + ban +
                '}';
    }

    //Only Checks if ids match. Not other fields
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
