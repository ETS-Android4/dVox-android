package com.dpearth.dvox.smartcontract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class PostContract extends Contract {
    public static final String BINARY = "0x60806040526000805534801561001457600080fd5b5061012b6040805190810160405280600f81526020017f57656c636f6d65206d65737361676500000000000000000000000000000000008152506040805190810160405280600781526020017f43726561746f7200000000000000000000000000000000000000000000000000815250606060405190810160405280602481526020017f48656c6c6f20576f726c642e2054686973206973206f7572206669727374207081526020017f6f73742e000000000000000000000000000000000000000000000000000000008152506040805190810160405280600b81526020017f6861736874616774657374000000000000000000000000000000000000000000815250610130640100000000026401000000009004565b6102b3565b6000805460019081018083556040805160e08101825282815260208082018a81528284018a9052606083018990526080830188905260a0830187905260c08301879052938652848152919094208451815591518051929361019993908501929190910190610218565b50604082015180516101b5916002840191602090910190610218565b50606082015180516101d1916003840191602090910190610218565b50608082015180516101ed916004840191602090910190610218565b5060a0820151600582015560c0909101516006909101805460ff191691151591909117905550505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061025957805160ff1916838001178555610286565b82800160010185558215610286579182015b8281111561028657825182559160200191906001019061026b565b50610292929150610296565b5090565b6102b091905b80821115610292576000815560010161029c565b90565b61096b806102c26000396000f3fe6080604052600436106100615763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630b1e7f8381146100665780630d57f91f1461025357806317906c2e1461049d578063f63ee2c2146104c4575b600080fd5b34801561007257600080fd5b506100906004803603602081101561008957600080fd5b50356104f4565b60405180888152602001806020018060200180602001806020018781526020018615151515815260200185810385528b818151815260200191508051906020019080838360005b838110156100ef5781810151838201526020016100d7565b50505050905090810190601f16801561011c5780820380516001836020036101000a031916815260200191505b5085810384528a5181528a516020918201918c019080838360005b8381101561014f578181015183820152602001610137565b50505050905090810190601f16801561017c5780820380516001836020036101000a031916815260200191505b5085810383528951815289516020918201918b019080838360005b838110156101af578181015183820152602001610197565b50505050905090810190601f1680156101dc5780820380516001836020036101000a031916815260200191505b5085810382528851815288516020918201918a019080838360005b8381101561020f5781810151838201526020016101f7565b50505050905090810190601f16801561023c5780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b34801561025f57600080fd5b5061049b6004803603608081101561027657600080fd5b81019060208101813564010000000081111561029157600080fd5b8201836020820111156102a357600080fd5b803590602001918460018302840111640100000000831117156102c557600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929594936020810193503591505064010000000081111561031857600080fd5b82018360208201111561032a57600080fd5b8035906020019184600183028401116401000000008311171561034c57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929594936020810193503591505064010000000081111561039f57600080fd5b8201836020820111156103b157600080fd5b803590602001918460018302840111640100000000831117156103d357600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929594936020810193503591505064010000000081111561042657600080fd5b82018360208201111561043857600080fd5b8035906020019184600183028401116401000000008311171561045a57600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061075c945050505050565b005b3480156104a957600080fd5b506104b2610844565b60408051918252519081900360200190f35b3480156104d057600080fd5b5061049b600480360360408110156104e757600080fd5b508035906020013561084a565b600160208181526000928352604092839020805481840180548651600296821615610100026000190190911695909504601f810185900485028601850190965285855290949193929091908301828280156105905780601f1061056557610100808354040283529160200191610590565b820191906000526020600020905b81548152906001019060200180831161057357829003601f168201915b50505060028085018054604080516020601f60001961010060018716150201909416959095049283018590048502810185019091528181529596959450909250908301828280156106225780601f106105f757610100808354040283529160200191610622565b820191906000526020600020905b81548152906001019060200180831161060557829003601f168201915b5050505060038301805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529495949350908301828280156106b25780601f10610687576101008083540402835291602001916106b2565b820191906000526020600020905b81548152906001019060200180831161069557829003601f168201915b5050505060048301805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529495949350908301828280156107425780601f1061071757610100808354040283529160200191610742565b820191906000526020600020905b81548152906001019060200180831161072557829003601f168201915b50505050600583015460069093015491929160ff16905087565b6000805460019081018083556040805160e08101825282815260208082018a81528284018a9052606083018990526080830188905260a0830187905260c0830187905293865284815291909420845181559151805192936107c5939085019291909101906108a4565b50604082015180516107e19160028401916020909101906108a4565b50606082015180516107fd9160038401916020909101906108a4565b50608082015180516108199160048401916020909101906108a4565b5060a0820151600582015560c0909101516006909101805460ff191691151591909117905550505050565b60005481565b806001148061085a575080600019145b156108a05760008281526001602052604090206005018054820190819055600919126108a0576000828152600160208190526040909120600601805460ff191690911790555b5050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106108e557805160ff1916838001178555610912565b82800160010185558215610912579182015b828111156109125782518255916020019190600101906108f7565b5061091e929150610922565b5090565b61093c91905b8082111561091e5760008155600101610928565b9056fea165627a7a7230582094640591acb6b44a231f856d9f29fcff909b656db1e93da5c5bfd2d64804517f0029";

    public static final String FUNC_POSTS = "posts";

    public static final String FUNC_POSTCOUNT = "postCount";

    public static final String FUNC_CREATEPOST = "createPost";

    public static final String FUNC_ADDVOTE = "addVote";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4", "0x18e8b3971630b5A975AEf6b708C3c8987b058aa8");
        _addresses.put("5777", "0x08Ce23df4EA94dc8677ee7F5202770587a361e37");
    }

    @Deprecated
    protected PostContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PostContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PostContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PostContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, String, String, String, String, BigInteger, Boolean>> posts(BigInteger param0) {
        final Function function = new Function(FUNC_POSTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple7<BigInteger, String, String, String, String, BigInteger, Boolean>>(function,
                new Callable<Tuple7<BigInteger, String, String, String, String, BigInteger, Boolean>>() {
                    @Override
                    public Tuple7<BigInteger, String, String, String, String, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, String, String, String, String, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (Boolean) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> postCount() {
        final Function function = new Function(FUNC_POSTCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> createPost(String _title, String _author, String _content, String _hashtag) {
        final Function function = new Function(
                FUNC_CREATEPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_author), 
                new org.web3j.abi.datatypes.Utf8String(_content), 
                new org.web3j.abi.datatypes.Utf8String(_hashtag)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addVote(BigInteger id, BigInteger i) {
        final Function function = new Function(
                FUNC_ADDVOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.generated.Int256(i)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PostContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PostContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PostContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PostContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PostContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PostContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PostContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PostContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PostContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PostContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<PostContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PostContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PostContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PostContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PostContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PostContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
