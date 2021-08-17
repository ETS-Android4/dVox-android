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
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple9;
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
    public static final String BINARY = "0x6080604052600080553480156200001557600080fd5b50620000a46040518060400160405280600f81526020016e57656c636f6d65206d65737361676560881b8152506040518060400160405280600781526020016621b932b0ba37b960c91b81525060405180606001604052806025815260200162000f3b6025913960408051808201909152600b81526a1a185cda1d1859dd195cdd60aa1b6020820152620000f2565b620000ec60016040518060400160405280600981526020016820b632b5b9b0b7323960b91b81525060405180606001604052806021815260200162000f1a60219139620001a4565b6200036a565b600080549080620001038362000340565b90915550506000805480825260016020818152604090932091825586519192620001359291840191908801906200025d565b5083516200014d90600283019060208701906200025d565b5082516200016590600383019060208601906200025d565b5081516200017d90600483019060208501906200025d565b50600060058201819055600682018190556007820155600801805460ff1916905550505050565b60008381526001602052604081206007810180549192620001c58362000340565b909155505060408051608081018252600783015480825260208083018781528385018790526000606085018190529283526009860182529390912082518155925180519293926200021d92600185019201906200025d565b50604082015180516200023b9160028401916020909101906200025d565b50606091909101516003909101805460ff191691151591909117905550505050565b8280546200026b9062000303565b90600052602060002090601f0160209004810192826200028f5760008555620002da565b82601f10620002aa57805160ff1916838001178555620002da565b82800160010185558215620002da579182015b82811115620002da578251825591602001919060010190620002bd565b50620002e8929150620002ec565b5090565b5b80821115620002e85760008155600101620002ed565b600181811c908216806200031857607f821691505b602082108114156200033a57634e487b7160e01b600052602260045260246000fd5b50919050565b60006000198214156200036357634e487b7160e01b600052601160045260246000fd5b5060010190565b610ba0806200037a6000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c80631ae9392d116100665780631ae9392d14610108578063488927531461011b578063d7bafcf11461013e578063dae39a741461017e578063ec859db5146101af57600080fd5b80630b1e7f83146100985780630d57f91f146100c95780631521e616146100de57806317906c2e146100f1575b600080fd5b6100ab6100a636600461091a565b6101c2565b6040516100c099989796959493929190610a4e565b60405180910390f35b6100dc6100d736600461086d565b61042c565b005b6100dc6100ec366004610933565b6104d4565b6100fa60005481565b6040519081526020016100c0565b6100dc61011636600461091a565b610587565b61012e6101293660046109a0565b6105ba565b6040516100c09493929190610a0f565b6100dc61014c3660046109a0565b6000918252600160209081526040808420928452600990920190529020600301805460ff81161560ff19909116179055565b6100dc61018c36600461091a565b6000908152600160205260409020600801805460ff19811660ff90911615179055565b6100dc6101bd36600461091a565b610714565b6001602081905260009182526040909120805491810180546101e390610ae8565b80601f016020809104026020016040519081016040528092919081815260200182805461020f90610ae8565b801561025c5780601f106102315761010080835404028352916020019161025c565b820191906000526020600020905b81548152906001019060200180831161023f57829003601f168201915b50505050509080600201805461027190610ae8565b80601f016020809104026020016040519081016040528092919081815260200182805461029d90610ae8565b80156102ea5780601f106102bf576101008083540402835291602001916102ea565b820191906000526020600020905b8154815290600101906020018083116102cd57829003601f168201915b5050505050908060030180546102ff90610ae8565b80601f016020809104026020016040519081016040528092919081815260200182805461032b90610ae8565b80156103785780601f1061034d57610100808354040283529160200191610378565b820191906000526020600020905b81548152906001019060200180831161035b57829003601f168201915b50505050509080600401805461038d90610ae8565b80601f01602080910402602001604051908101604052809291908181526020018280546103b990610ae8565b80156104065780601f106103db57610100808354040283529160200191610406565b820191906000526020600020905b8154815290600101906020018083116103e957829003601f168201915b505050506005830154600684015460078501546008909501549394919390925060ff1689565b60008054908061043b83610b23565b9091555050600080548082526001602081815260409093209182558651919261046b929184019190880190610747565b5083516104819060028301906020870190610747565b5082516104979060038301906020860190610747565b5081516104ad9060048301906020850190610747565b50600060058201819055600682018190556007820155600801805460ff1916905550505050565b600083815260016020526040812060078101805491926104f383610b23565b909155505060408051608081018252600783015480825260208083018781528385018790526000606085018190529283526009860182529390912082518155925180519293926105499260018501920190610747565b5060408201518051610565916002840191602090910190610747565b50606091909101516003909101805460ff191691151591909117905550505050565b6000818152600160208190526040909120600601546105a591610ad0565b60009182526001602052604090912060060155565b6000828152600160208181526040808420858552600901909152822080549181018054929360609384939192906105f090610ae8565b80601f016020809104026020016040519081016040528092919081815260200182805461061c90610ae8565b80156106695780601f1061063e57610100808354040283529160200191610669565b820191906000526020600020905b81548152906001019060200180831161064c57829003601f168201915b5050505050935080600201805461067f90610ae8565b80601f01602080910402602001604051908101604052809291908181526020018280546106ab90610ae8565b80156106f85780601f106106cd576101008083540402835291602001916106f8565b820191906000526020600020905b8154815290600101906020018083116106db57829003601f168201915b5050506003909301549194505060ff1691505092959194509250565b60008181526001602081905260409091206005015461073291610ad0565b60009182526001602052604090912060050155565b82805461075390610ae8565b90600052602060002090601f01602090048101928261077557600085556107bb565b82601f1061078e57805160ff19168380011785556107bb565b828001600101855582156107bb579182015b828111156107bb5782518255916020019190600101906107a0565b506107c79291506107cb565b5090565b5b808211156107c757600081556001016107cc565b600082601f8301126107f157600080fd5b813567ffffffffffffffff8082111561080c5761080c610b54565b604051601f8301601f19908116603f0116810190828211818310171561083457610834610b54565b8160405283815286602085880101111561084d57600080fd5b836020870160208301376000602085830101528094505050505092915050565b6000806000806080858703121561088357600080fd5b843567ffffffffffffffff8082111561089b57600080fd5b6108a7888389016107e0565b955060208701359150808211156108bd57600080fd5b6108c9888389016107e0565b945060408701359150808211156108df57600080fd5b6108eb888389016107e0565b9350606087013591508082111561090157600080fd5b5061090e878288016107e0565b91505092959194509250565b60006020828403121561092c57600080fd5b5035919050565b60008060006060848603121561094857600080fd5b83359250602084013567ffffffffffffffff8082111561096757600080fd5b610973878388016107e0565b9350604086013591508082111561098957600080fd5b50610996868287016107e0565b9150509250925092565b600080604083850312156109b357600080fd5b50508035926020909101359150565b6000815180845260005b818110156109e8576020818501810151868301820152016109cc565b818111156109fa576000602083870101525b50601f01601f19169290920160200192915050565b848152608060208201526000610a2860808301866109c2565b8281036040840152610a3a81866109c2565b915050821515606083015295945050505050565b60006101208b8352806020840152610a688184018c6109c2565b90508281036040840152610a7c818b6109c2565b90508281036060840152610a90818a6109c2565b90508281036080840152610aa481896109c2565b9150508560a08301528460c08301528360e08301528215156101008301529a9950505050505050505050565b60008219821115610ae357610ae3610b3e565b500190565b600181811c90821680610afc57607f821691505b60208210811415610b1d57634e487b7160e01b600052602260045260246000fd5b50919050565b6000600019821415610b3757610b37610b3e565b5060010190565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052604160045260246000fdfea2646970667358221220ceb229988d36fbc4a812b70b7aebe57534221bee3ab3de0944cb88f0fcc1f59864736f6c6343000807003348656c6c6f2c20746869732069732074686520666972737420636f6d6d656e742148656c6c6f20576f726c6421212054686973206973206f757220666972737420706f73742e";

    public static final String FUNC_POSTCOUNT = "postCount";

    public static final String FUNC_POSTS = "posts";

    public static final String FUNC_CREATEPOST = "createPost";

    public static final String FUNC_UPVOTE = "upVote";

    public static final String FUNC_DOWNVOTE = "downVote";

    public static final String FUNC_ADDCOMMENT = "addComment";

    public static final String FUNC_GETCOMMENT = "getComment";

    public static final String FUNC_BANPOST = "banPost";

    public static final String FUNC_BANCOMMENT = "banComment";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4", "0x843b48B4270d35059Fa7527EbC2A31ef2cD20485");
        _addresses.put("5777", "0x9865970d7C5AfdFeBcAb5a1EB39e354Fdcc34C70");
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

    public RemoteFunctionCall<BigInteger> postCount() {
        final Function function = new Function(FUNC_POSTCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple9<BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger, Boolean>> posts(BigInteger param0) {
        final Function function = new Function(FUNC_POSTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple9<BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple9<BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple9<BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<BigInteger, String, String, String, String, BigInteger, BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (Boolean) results.get(8).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createPost(String _title, String _author, String _message, String _hashtag) {
        final Function function = new Function(
                FUNC_CREATEPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_author), 
                new org.web3j.abi.datatypes.Utf8String(_message), 
                new org.web3j.abi.datatypes.Utf8String(_hashtag)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> upVote(BigInteger id) {
        final Function function = new Function(
                FUNC_UPVOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> downVote(BigInteger id) {
        final Function function = new Function(
                FUNC_DOWNVOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addComment(BigInteger _postID, String _comment_author, String _comment_message) {
        final Function function = new Function(
                FUNC_ADDCOMMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_postID), 
                new org.web3j.abi.datatypes.Utf8String(_comment_author), 
                new org.web3j.abi.datatypes.Utf8String(_comment_message)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, String, String, Boolean>> getComment(BigInteger postID, BigInteger commentID) {
        final Function function = new Function(FUNC_GETCOMMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(postID), 
                new org.web3j.abi.datatypes.generated.Uint256(commentID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, String, String, Boolean>>(function,
                new Callable<Tuple4<BigInteger, String, String, Boolean>>() {
                    @Override
                    public Tuple4<BigInteger, String, String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, String, String, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> banPost(BigInteger postID) {
        final Function function = new Function(
                FUNC_BANPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(postID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> banComment(BigInteger postID, BigInteger commentID) {
        final Function function = new Function(
                FUNC_BANCOMMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(postID), 
                new org.web3j.abi.datatypes.generated.Uint256(commentID)), 
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
