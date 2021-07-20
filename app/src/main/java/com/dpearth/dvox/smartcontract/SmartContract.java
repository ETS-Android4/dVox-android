package com.dpearth.dvox.smartcontract;

import android.util.Log;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.Tuple;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import java8.util.concurrent.CompletableFuture;

/**
 * PROVIDE DESCRIPTION
*
*
*
*   1 - uint id;
    2 - string title;
    3 - string author;
    4 - string message;
    5 - string hashtag;
    6 - int votes;
    7 - bool ban;
*
* */
public class SmartContract {

    private PostContract postContract;

    public SmartContract(String contractAddress, String infuraURL, Credentials credentials) {
        Web3j web3j = Web3j.build(new HttpService(infuraURL));
        this.postContract = PostContract.load(contractAddress, web3j, credentials, new DefaultGasProvider());
    }

    /** PROVIDE COMMENTS
     *
     */
    public BigInteger getPostCount(){
        BigInteger getNumberOfPosts = BigInteger.valueOf(0L);
        try {
            getNumberOfPosts = postContract.postCount().sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return getNumberOfPosts;
    }

    /** PROVIDE COMMENTS
     *
     * @param id
     */
    public Post getPost(long id){

        Post post = new Post();

        try {
            Tuple7<BigInteger, String, String, String, String, BigInteger, Boolean> contractPost = postContract.posts((BigInteger.valueOf(id))).sendAsync().get();

            post.setId(contractPost.component1());
            post.setTitle(contractPost.component2());
            post.setAuthor(contractPost.component3());
            post.setMessage(contractPost.component4());
            post.setHashtag(contractPost.component5());
            post.setVotes(contractPost.component6());
            post.setBan(contractPost.component7());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return post;
    }

    /** PROVIDE COMMENTS
     *
     * @param id
     * @param vote
     */
    public boolean addVote(long id, int vote){
        if (vote == 1 || vote == -1 ) {
            try {
                postContract.addVote(BigInteger.valueOf(id), BigInteger.valueOf(vote)).sendAsync().get();
            } catch (Exception error) {
                Log.d("SMART_CONTRACT_DEBUG", "Create Post Error: ", error);
                return false;
            }
            return true;
        }
        return false;
    }

    /** PROVIDE COMMENTS
     *
     * @param _title
     * @param _author
     * @param _message
     * @param _hashtag
     *
     */
    public boolean createPost(String _title, String _author, String _message, String _hashtag){
        try {
            postContract.createPost(_title, _author, _message, _hashtag).sendAsync().get();
        } catch (Exception error) {
            Log.d("SMART_CONTRACT_DEBUG", "Add Vote Error: ", error);
            return false;
        }
        return true;
    }


}
