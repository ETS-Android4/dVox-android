package com.dpearth.dvox.smartcontract;

import java.math.BigInteger;

public class Post {

    private BigInteger id;
    private String title;
    private String author;
    private String message;
    private String hashtag;
    private int votes;
    private boolean ban;

    public Post(BigInteger id, String title, String author, String message, String hashtag, int votes, boolean ban) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.message = message;
        this.hashtag = hashtag;
        this.votes = votes;
        this.ban = ban;


    }
}
