package com.dpearth.dvox.livedata;

import io.paperdb.Paper;

public class Statistics {

    private int postsCreated = 0;
    private int upVoted = 0;
    private int downVoted = 0;
    private int commentsAdded = 0;

    public Statistics(){
        postsCreated = Paper.book("Statistics").read("postsCreated", 0);
        upVoted = Paper.book("Statistics").read("upVoted", 0);
        downVoted = Paper.book("Statistics").read("downVoted", 0);
        commentsAdded = Paper.book("Statistics").read("commentsAdded", 0);
    }

    public void resetStatistics(){
        postsCreated = 0;
        commentsAdded = 0;

        setPostsCreated(postsCreated);
        setCommentsAdded(commentsAdded);
    }


    public void upPostsCreated() {
        Paper.book("Statistics").write("postsCreated", this.postsCreated + 1);
        this.postsCreated++;
    }

    public void upUpVoted() {
        Paper.book("Statistics").write("upVoted", this.upVoted + 1);
        this.upVoted++;
    }

    public void upDownVoted() {
        Paper.book("Statistics").write("downVoted", this.downVoted + 1);
        this.downVoted++;
    }

    public void downUpVoted() {
        Paper.book("Statistics").write("upVoted", this.upVoted - 1);
        this.upVoted--;
    }

    public void downDownVoted() {
        Paper.book("Statistics").write("downVoted", this.downVoted - 1);
        this.downVoted--;
    }

    public void upCommentsAdded() {
        Paper.book("Statistics").write("commentsAdded", this.commentsAdded + 1);
        this.commentsAdded++;
    }

    public void setPostsCreated(int postsCreated) {
        Paper.book("Statistics").write("postsCreated", postsCreated);
        this.postsCreated = postsCreated;
    }

    public void setUpVoted(int upVoted) {
        Paper.book("Statistics").write("upVoted", upVoted);
        this.upVoted = upVoted;
    }

    public void setDownVoted(int downVoted) {
        Paper.book("Statistics").write("downVoted", downVoted);
        this.downVoted = downVoted;
    }

    public void setCommentsAdded(int commentsAdded) {
        Paper.book("Statistics").write("commentsAdded", commentsAdded);
        this.commentsAdded = commentsAdded;
    }

    public int getPostsCreated() {
        return postsCreated;
    }

    public int getUpVoted() {
        return upVoted;
    }

    public int getDownVoted() {
        return downVoted;
    }

    public int getCommentsAdded() {
        return commentsAdded;
    }
}
