package com.dpearth.dvox.smartcontract;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 *  A simple post class.
 *
 *  @author Aleksandr Molchagin
 *  @version 06.27.2021
 */


public class Post {
    private BigInteger id;
    private static BigInteger counter = BigInteger.valueOf(1l);
    private String title;
    private String author;
    private String message;
    private String hashtag;
    private BigInteger votes;
    private boolean ban;

    //////////////////
    /* Constructors */
    //////////////////

    public Post(String title, String author, String message, String hashtag) {

        //Auto Generating ID
        this.id = BigInteger.valueOf(0);
        id = id.add(counter);

        this.title = title;
        this.author = author;
        this.message = message;
        this.hashtag = hashtag;

        this.votes = BigInteger.valueOf(0);
        this.ban = false;

    }

    public Post(){
        this(null, null, null, null);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id);
    }
}
