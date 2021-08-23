package com.dpearth.dvox;

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
    public static final String BINARY = "0x6080604052600080553480156200001557600080fd5b50620000a46040518060400160405280600f81526020016e57656c636f6d65206d65737361676560881b8152506040518060400160405280600781526020016621b932b0ba37b960c91b81525060405180606001604052806025815260200162000e3c6025913960408051808201909152600b81526a1a185cda1d1859dd195cdd60aa1b6020820152620000f2565b620000ec60016040518060400160405280600981526020016820b632b5b9b0b7323960b91b81525060405180606001604052806021815260200162000e1b6021913962000196565b6200035c565b600080549080620001038362000332565b90915550506000805480825260016020818152604090932091825586519192620001359291840191908801906200024f565b5083516200014d90600283019060208701906200024f565b5082516200016590600383019060208601906200024f565b5081516200017d90600483019060208501906200024f565b5060006005820155600601805460ff1916905550505050565b60008381526001602052604081206005810180549192620001b78362000332565b909155505060408051608081018252600583015480825260208083018781528385018790526000606085018190529283526007860182529390912082518155925180519293926200020f92600185019201906200024f565b50604082015180516200022d9160028401916020909101906200024f565b50606091909101516003909101805460ff191691151591909117905550505050565b8280546200025d90620002f5565b90600052602060002090601f016020900481019282620002815760008555620002cc565b82601f106200029c57805160ff1916838001178555620002cc565b82800160010185558215620002cc579182015b82811115620002cc578251825591602001919060010190620002af565b50620002da929150620002de565b5090565b5b80821115620002da5760008155600101620002df565b600181811c908216806200030a57607f821691505b602082108114156200032c57634e487b7160e01b600052602260045260246000fd5b50919050565b60006000198214156200035557634e487b7160e01b600052601160045260246000fd5b5060010190565b610aaf806200036c6000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806317906c2e1161005b57806317906c2e146100d957806348892753146100f0578063d7bafcf114610113578063dae39a741461015357600080fd5b80630b1e7f83146100825780630d57f91f146100b15780631521e616146100c6575b600080fd5b61009561009036600461085c565b610184565b6040516100a89796959493929190610990565b60405180910390f35b6100c46100bf3660046107af565b6103e2565b005b6100c46100d4366004610875565b61047c565b6100e260005481565b6040519081526020016100a8565b6101036100fe3660046108e2565b61052f565b6040516100a89493929190610951565b6100c46101213660046108e2565b6000918252600160209081526040808420928452600790920190529020600301805460ff81161560ff19909116179055565b6100c461016136600461085c565b6000908152600160205260409020600601805460ff19811660ff90911615179055565b6001602081905260009182526040909120805491810180546101a5906109ff565b80601f01602080910402602001604051908101604052809291908181526020018280546101d1906109ff565b801561021e5780601f106101f35761010080835404028352916020019161021e565b820191906000526020600020905b81548152906001019060200180831161020157829003601f168201915b505050505090806002018054610233906109ff565b80601f016020809104026020016040519081016040528092919081815260200182805461025f906109ff565b80156102ac5780601f10610281576101008083540402835291602001916102ac565b820191906000526020600020905b81548152906001019060200180831161028f57829003601f168201915b5050505050908060030180546102c1906109ff565b80601f01602080910402602001604051908101604052809291908181526020018280546102ed906109ff565b801561033a5780601f1061030f5761010080835404028352916020019161033a565b820191906000526020600020905b81548152906001019060200180831161031d57829003601f168201915b50505050509080600401805461034f906109ff565b80601f016020809104026020016040519081016040528092919081815260200182805461037b906109ff565b80156103c85780601f1061039d576101008083540402835291602001916103c8565b820191906000526020600020905b8154815290600101906020018083116103ab57829003601f168201915b50505050600583015460069093015491929160ff16905087565b6000805490806103f183610a3a565b90915550506000805480825260016020818152604090932091825586519192610421929184019190880190610689565b5083516104379060028301906020870190610689565b50825161044d9060038301906020860190610689565b5081516104639060048301906020850190610689565b5060006005820155600601805460ff1916905550505050565b6000838152600160205260408120600581018054919261049b83610a3a565b909155505060408051608081018252600583015480825260208083018781528385018790526000606085018190529283526007860182529390912082518155925180519293926104f19260018501920190610689565b506040820151805161050d916002840191602090910190610689565b50606091909101516003909101805460ff191691151591909117905550505050565b600082815260016020818152604080842085855260070190915282208054918101805492936060938493919290610565906109ff565b80601f0160208091040260200160405190810160405280929190818152602001828054610591906109ff565b80156105de5780601f106105b3576101008083540402835291602001916105de565b820191906000526020600020905b8154815290600101906020018083116105c157829003601f168201915b505050505093508060020180546105f4906109ff565b80601f0160208091040260200160405190810160405280929190818152602001828054610620906109ff565b801561066d5780601f106106425761010080835404028352916020019161066d565b820191906000526020600020905b81548152906001019060200180831161065057829003601f168201915b5050506003909301549194505060ff1691505092959194509250565b828054610695906109ff565b90600052602060002090601f0160209004810192826106b757600085556106fd565b82601f106106d057805160ff19168380011785556106fd565b828001600101855582156106fd579182015b828111156106fd5782518255916020019190600101906106e2565b5061070992915061070d565b5090565b5b80821115610709576000815560010161070e565b600082601f83011261073357600080fd5b813567ffffffffffffffff8082111561074e5761074e610a63565b604051601f8301601f19908116603f0116810190828211818310171561077657610776610a63565b8160405283815286602085880101111561078f57600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080608085870312156107c557600080fd5b843567ffffffffffffffff808211156107dd57600080fd5b6107e988838901610722565b955060208701359150808211156107ff57600080fd5b61080b88838901610722565b9450604087013591508082111561082157600080fd5b61082d88838901610722565b9350606087013591508082111561084357600080fd5b5061085087828801610722565b91505092959194509250565b60006020828403121561086e57600080fd5b5035919050565b60008060006060848603121561088a57600080fd5b83359250602084013567ffffffffffffffff808211156108a957600080fd5b6108b587838801610722565b935060408601359150808211156108cb57600080fd5b506108d886828701610722565b9150509250925092565b600080604083850312156108f557600080fd5b50508035926020909101359150565b6000815180845260005b8181101561092a5760208185018101518683018201520161090e565b8181111561093c576000602083870101525b50601f01601f19169290920160200192915050565b84815260806020820152600061096a6080830186610904565b828103604084015261097c8186610904565b915050821515606083015295945050505050565b87815260e0602082015260006109a960e0830189610904565b82810360408401526109bb8189610904565b905082810360608401526109cf8188610904565b905082810360808401526109e38187610904565b60a0840195909552505090151560c09091015295945050505050565b600181811c90821680610a1357607f821691505b60208210811415610a3457634e487b7160e01b600052602260045260246000fd5b50919050565b6000600019821415610a5c57634e487b7160e01b600052601160045260246000fd5b5060010190565b634e487b7160e01b600052604160045260246000fdfea2646970667358221220d42cf287c77687e9cef498d83c2341576f6aaa015a5b86dea830d6eadff4aeff64736f6c6343000807003348656c6c6f2c20746869732069732074686520666972737420636f6d6d656e742148656c6c6f20576f726c6421212054686973206973206f757220666972737420706f73742e";

    public static final String FUNC_POSTCOUNT = "postCount";

    public static final String FUNC_POSTS = "posts";

    public static final String FUNC_CREATEPOST = "createPost";

    public static final String FUNC_ADDCOMMENT = "addComment";

    public static final String FUNC_GETCOMMENT = "getComment";

    public static final String FUNC_BANPOST = "banPost";

    public static final String FUNC_BANCOMMENT = "banComment";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4", "0x47D3e11D792d7b4B808775629Fcd5A36CfAf00E6");
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

    public RemoteFunctionCall<Tuple7<BigInteger, String, String, String, String, BigInteger, Boolean>> posts(BigInteger param0) {
        final Function function = new Function(FUNC_POSTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
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
