package com.dpearth.dvox.smartcontract;

import java.math.BigInteger;

/**
 *
 *  A simple post class.
 *
 *  @author Aleksandr Molchagin
 *  @version 06.27.2021
 */


public class Post {
    BigInteger id;
    String title;
    String author;
    String message;
    String hashtag;
    BigInteger votes;
    boolean ban;

    //////////////////
    /* Constructors */
    //////////////////

    /**
     * Constructs a new post.
     *
     *      @param   _title   (String) title of the post
     *      @param   _author    (String) author of the post
     *      @param   _message   (String) message of the post
     */
    public Post(BigInteger _id, String _title, String _author, String _message, String _hashtag, BigInteger _votes, boolean _ban){
        this.id = _id;
        this.title = _title;
        this.author = _author;
        this.message = _message;
        this.hashtag = _hashtag;
        this.votes = _votes;
        this.ban = _ban;
    }

    public Post(){

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

    public BigInteger getVotes() {
        return votes;
    }

    public void setVotes(BigInteger votes) {
        this.votes = votes;
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
                ", votes=" + votes +
                ", ban=" + ban +
                '}';
    }
}
