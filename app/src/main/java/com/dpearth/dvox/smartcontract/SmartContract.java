package com.dpearth.dvox.smartcontract;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

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

    public String getPost(long postNumber){
        String message = null;
        try {
//            postContract.posts(BigInteger.valueOf(1L)).sendAsync().get();
            message = postContract.posts(BigInteger.valueOf(postNumber)).sendAsync().get().component4();
//            (String) results.get(3).getValue()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return message;
    }


}
