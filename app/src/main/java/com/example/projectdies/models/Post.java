package com.example.projectdies.models;

/**
 *
 *  A simple post class.
 *
 *  @author Aleksandr Molchagin
 *  @version 06.27.2021
 */


public class Post {
    int id;
    String author;
    String message;
    int votes;
    boolean ban;

    //////////////////
    /* Constructors */
    //////////////////

    /**
     * Constructs a new post.
     *
     *      @param   _author    (String) author of the post
     *      @param   _message   (String) message of the post
     */
    public Post(String _author, String _message){
        this.id = 0;
        this.author = _author;
        this.message = _message;
        this.votes = 0;
        this.ban = false;
    }

    /////////////
    /* Methods */
    /////////////
    /**
     * Gets the post's id.
     *      @return id (int)
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the post's author.
     *      @return author (String)
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the post's message.
     *      @return message (String)
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the post's votes.
     *      @return votes (int)
     */
    public int getVotes() {
        return votes;
    }

    /**
     * Add 1 vote to the post.
     */
    public void addVote() {
        this.votes++;
    }

    /**
     * Deduct 1 vote to the post.
     */
    public void deductVote() {
        this.votes--;
    }

    /**
     * Checks if the post is banned. True = banned; False = !banned.
     *      @return ban (boolean)
     */
    public boolean isBanned() {
        return ban;
    }
}
