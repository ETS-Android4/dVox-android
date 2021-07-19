package com.dpearth.dvox.models.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dpearth.dvox.LoginActivity;
import com.dpearth.dvox.MainActivity;
import com.dpearth.dvox.R;
import com.dpearth.dvox.smartcontract.PostContract;
import com.dpearth.dvox.smartcontract.SmartContract;
import com.kenai.jffi.Main;
import com.muddzdev.styleabletoast.StyleableToast;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import java8.util.concurrent.CompletableFuture;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // The address of our contract
        String contractAddress = "";
        // The infura address
        String infuraURL = "";
        // Connect to the ethereum network
        Web3j web3j = Web3j.build(new HttpService(infuraURL));
        // Wallet credentials
        Credentials credentials = Credentials.create("");
        // Gas limit
        BigInteger gasLimit = BigInteger.valueOf(20_000_000_000L);
        // Price limit
        BigInteger gasPrice = BigInteger.valueOf(4300000);
        // Get the our contract from Java wrapper file (./smartcontract/PostContract)
        PostContract postContract = PostContract.load(contractAddress, web3j, credentials, gasLimit, gasPrice);
        // Send request
        CompletableFuture<BigInteger> getNumberOfPosts = postContract.postCount().sendAsync();



        SmartContract smartContract = new SmartContract(contractAddress, infuraURL, credentials,
                BigInteger.valueOf(200000000000000l), BigInteger.valueOf(10000));




            StyleableToast.makeText(getActivity(), "POST MESSAGE: " + smartContract.getPost(1), Toast.LENGTH_LONG, R.style.LoginToast).show();

    }

    private String getAddress(Credentials credentials) {
        return credentials.getAddress();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_post, container, false);
    }
}