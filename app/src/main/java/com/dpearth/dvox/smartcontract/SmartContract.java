package com.dpearth.dvox.smartcontract;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.Tuple;
import org.web3j.tuples.generated.Tuple7;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import java8.util.concurrent.CompletableFuture;

/*
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

    public SmartContract(String contractAddress, String infuraURL, Credentials credentials, BigInteger gasLimit, BigInteger gasPrice) {
        Web3j web3j = Web3j.build(new HttpService(infuraURL));
        this.postContract = PostContract.load(contractAddress, web3j, credentials, gasLimit, gasPrice);
    }


    public BigInteger postCount(){
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

    public Post getPost(long postNumber){

        Post post = new Post();

        try {
            Tuple7<BigInteger, String, String, String, String, BigInteger, Boolean> contractPost = postContract.posts((BigInteger.valueOf(postNumber))).sendAsync().get();

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


}
