package com.dpearth.dvox.smartcontract;

import android.content.SharedPreferences;
import android.util.Log;

import org.web3j.abi.datatypes.Int;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

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

    private boolean loaded = false;

    public SmartContract(SharedPreferences preferences) {

            String Credentials = preferences.getString("Credentials", "error");
            String InfuraURL = preferences.getString("InfuraURL", "error");
            String ContractAddress = preferences.getString("Address", "error");

            if (!ContractAddress.equals("error") && !Credentials.equals("error") && !InfuraURL.equals("error")) {
                Web3j web3j = Web3j.build(new HttpService(InfuraURL));
                Credentials credentials = org.web3j.crypto.Credentials.create(Credentials);
                postContract = PostContract.load(ContractAddress, web3j, credentials, new DefaultGasProvider());
                loaded = true;
            }
    }

    /** PROVIDE COMMENTS
     *
     */
    public int getPostCount(){
        if (loaded == true) {
            BigInteger getNumberOfPosts = BigInteger.valueOf(0L);
            try {
                getNumberOfPosts = postContract.postCount().sendAsync().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return (getNumberOfPosts.intValue())-1;

        }
        return -1;
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
