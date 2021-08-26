package com.dpearth.dvox.smartcontract;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private BigInteger id;

    private String title;
    private String author;
    private String message;
    private String hashtag;

    private BigInteger upVotes;
    private BigInteger downVotes;
    private BigInteger commentCount;
    private boolean ban;

    @ColumnInfo(name = "comments_list")//Rename column
    private List<Comment> comments;

    //////////////////
    /* Constructor for @Entity */
    //////////////////

    public Post(String title, String author, String message, String hashtag) {

        this.id = id;

        this.title = title;
        this.author = author;
        this.message = message;
        this.hashtag = hashtag;

        this.upVotes = BigInteger.valueOf(0);
        this.downVotes = BigInteger.valueOf(0);
        this.commentCount = BigInteger.valueOf(0);
        this.ban = false;
        this.comments = new ArrayList<>();

    }


    //////////////////
    /* Constructors */
    //////////////////

    public Post(){
        this(null, null, null, null, null);
    }

    public Post(BigInteger id, String title, String author, String message, String hashtag) {

        this.id = id;

        this.title = title;
        this.author = author;
        this.message = message;
        this.hashtag = hashtag;

        this.upVotes = BigInteger.valueOf(0);
        this.downVotes = BigInteger.valueOf(0);
        this.commentCount = BigInteger.valueOf(0);
        this.ban = false;
        this.comments = new ArrayList<>();

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public BigInteger getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(BigInteger upVotes) {
        this.upVotes = upVotes;
    }

    public BigInteger getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(BigInteger downVotes) {
        this.downVotes = downVotes;
    }

    public BigInteger getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(BigInteger commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", ban=" + ban +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return ban == post.ban && Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(author, post.author) && Objects.equals(message, post.message) && Objects.equals(hashtag, post.hashtag) && Objects.equals(upVotes, post.upVotes) && Objects.equals(downVotes, post.downVotes) && Objects.equals(comments, post.comments);
    }

}
