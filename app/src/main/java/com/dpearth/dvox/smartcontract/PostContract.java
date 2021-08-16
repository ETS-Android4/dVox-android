package com.dpearth.dvox;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
    public static final String BINARY = "0x6080604052600080553480156200001557600080fd5b50620000a46040518060400160405280600f81526020016e57656c636f6d65206d65737361676560881b8152506040518060400160405280600781526020016621b932b0ba37b960c91b81525060405180606001604052806025815260200162000ea06025913960408051808201909152600b81526a1a185cda1d1859dd195cdd60aa1b6020820152620000f2565b620000ec60016040518060400160405280600981526020016820b632b5b9b0b7323960b91b81525060405180606001604052806021815260200162000e7f60219139620001a4565b6200034a565b600080549080620001038362000320565b90915550506000805480825260016020818152604090932091825586519192620001359291840191908801906200023d565b5083516200014d90600283019060208701906200023d565b5082516200016590600383019060208601906200023d565b5081516200017d90600483019060208501906200023d565b50600060058201819055600682018190556007820155600801805460ff1916905550505050565b60008381526001602052604081206007810180549192620001c58362000320565b9091555050604080516060810182526007830154808252602080830187815283850187905260009283526009860182529390912082518155925180519293926200021692600185019201906200023d565b5060408201518051620002349160028401916020909101906200023d565b50505050505050565b8280546200024b90620002e3565b90600052602060002090601f0160209004810192826200026f5760008555620002ba565b82601f106200028a57805160ff1916838001178555620002ba565b82800160010185558215620002ba579182015b82811115620002ba5782518255916020019190600101906200029d565b50620002c8929150620002cc565b5090565b5b80821115620002c85760008155600101620002cd565b600181811c90821680620002f857607f821691505b602082108114156200031a57634e487b7160e01b600052602260045260246000fd5b50919050565b60006000198214156200034357634e487b7160e01b600052601160045260246000fd5b5060010190565b610b25806200035a6000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806317906c2e1161005b57806317906c2e146100db5780631ae9392d146100f25780634889275314610105578063ec859db51461012557600080fd5b80630b1e7f83146100825780630d57f91f146100b35780631521e616146100c8575b600080fd5b610095610090366004610892565b610138565b6040516100aa999897969594939291906109d3565b60405180910390f35b6100c66100c13660046107e5565b6103a2565b005b6100c66100d63660046108ab565b61044a565b6100e460005481565b6040519081526020016100aa565b6100c6610100366004610892565b6104dd565b610118610113366004610918565b610510565b6040516100aa9190610987565b6100c6610133366004610892565b61068c565b60016020819052600091825260409091208054918101805461015990610a6d565b80601f016020809104026020016040519081016040528092919081815260200182805461018590610a6d565b80156101d25780601f106101a7576101008083540402835291602001916101d2565b820191906000526020600020905b8154815290600101906020018083116101b557829003601f168201915b5050505050908060020180546101e790610a6d565b80601f016020809104026020016040519081016040528092919081815260200182805461021390610a6d565b80156102605780601f1061023557610100808354040283529160200191610260565b820191906000526020600020905b81548152906001019060200180831161024357829003601f168201915b50505050509080600301805461027590610a6d565b80601f01602080910402602001604051908101604052809291908181526020018280546102a190610a6d565b80156102ee5780601f106102c3576101008083540402835291602001916102ee565b820191906000526020600020905b8154815290600101906020018083116102d157829003601f168201915b50505050509080600401805461030390610a6d565b80601f016020809104026020016040519081016040528092919081815260200182805461032f90610a6d565b801561037c5780601f106103515761010080835404028352916020019161037c565b820191906000526020600020905b81548152906001019060200180831161035f57829003601f168201915b505050506005830154600684015460078501546008909501549394919390925060ff1689565b6000805490806103b183610aa8565b909155505060008054808252600160208181526040909320918255865191926103e19291840191908801906106bf565b5083516103f790600283019060208701906106bf565b50825161040d90600383019060208601906106bf565b50815161042390600483019060208501906106bf565b50600060058201819055600682018190556007820155600801805460ff1916905550505050565b6000838152600160205260408120600781018054919261046983610aa8565b9091555050604080516060810182526007830154808252602080830187815283850187905260009283526009860182529390912082518155925180519293926104b892600185019201906106bf565b50604082015180516104d49160028401916020909101906106bf565b50505050505050565b6000818152600160208190526040909120600601546104fb91610a55565b60009182526001602052604090912060060155565b61053460405180606001604052806000815260200160608152602001606081525090565b60008381526001602081815260408084208685526009018252928390208351606081019094528054845291820180549184019161057090610a6d565b80601f016020809104026020016040519081016040528092919081815260200182805461059c90610a6d565b80156105e95780601f106105be576101008083540402835291602001916105e9565b820191906000526020600020905b8154815290600101906020018083116105cc57829003601f168201915b5050505050815260200160028201805461060290610a6d565b80601f016020809104026020016040519081016040528092919081815260200182805461062e90610a6d565b801561067b5780601f106106505761010080835404028352916020019161067b565b820191906000526020600020905b81548152906001019060200180831161065e57829003601f168201915b505050505081525050905092915050565b6000818152600160208190526040909120600501546106aa91610a55565b60009182526001602052604090912060050155565b8280546106cb90610a6d565b90600052602060002090601f0160209004810192826106ed5760008555610733565b82601f1061070657805160ff1916838001178555610733565b82800160010185558215610733579182015b82811115610733578251825591602001919060010190610718565b5061073f929150610743565b5090565b5b8082111561073f5760008155600101610744565b600082601f83011261076957600080fd5b813567ffffffffffffffff8082111561078457610784610ad9565b604051601f8301601f19908116603f011681019082821181831017156107ac576107ac610ad9565b816040528381528660208588010111156107c557600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080608085870312156107fb57600080fd5b843567ffffffffffffffff8082111561081357600080fd5b61081f88838901610758565b9550602087013591508082111561083557600080fd5b61084188838901610758565b9450604087013591508082111561085757600080fd5b61086388838901610758565b9350606087013591508082111561087957600080fd5b5061088687828801610758565b91505092959194509250565b6000602082840312156108a457600080fd5b5035919050565b6000806000606084860312156108c057600080fd5b83359250602084013567ffffffffffffffff808211156108df57600080fd5b6108eb87838801610758565b9350604086013591508082111561090157600080fd5b5061090e86828701610758565b9150509250925092565b6000806040838503121561092b57600080fd5b50508035926020909101359150565b6000815180845260005b8181101561096057602081850181015186830182015201610944565b81811115610972576000602083870101525b50601f01601f19169290920160200192915050565b602081528151602082015260006020830151606060408401526109ad608084018261093a565b90506040840151601f198483030160608501526109ca828261093a565b95945050505050565b60006101208b83528060208401526109ed8184018c61093a565b90508281036040840152610a01818b61093a565b90508281036060840152610a15818a61093a565b90508281036080840152610a29818961093a565b9150508560a08301528460c08301528360e08301528215156101008301529a9950505050505050505050565b60008219821115610a6857610a68610ac3565b500190565b600181811c90821680610a8157607f821691505b60208210811415610aa257634e487b7160e01b600052602260045260246000fd5b50919050565b6000600019821415610abc57610abc610ac3565b5060010190565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052604160045260246000fdfea2646970667358221220a5d74c3b53c27953fefbe286900ca2df91afb7b84d1ecd2a485f942ce7a2b53864736f6c6343000807003348656c6c6f2c20746869732069732074686520666972737420636f6d6d656e742148656c6c6f20576f726c6421212054686973206973206f757220666972737420706f73742e";

    public static final String FUNC_POSTCOUNT = "postCount";

    public static final String FUNC_POSTS = "posts";

    public static final String FUNC_CREATEPOST = "createPost";

    public static final String FUNC_UPVOTE = "upVote";

    public static final String FUNC_DOWNVOTE = "downVote";

    public static final String FUNC_ADDCOMMENT = "addComment";

    public static final String FUNC_GETCOMMENT = "getComment";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4", "0x030922C053FCb8F8947060B8108a5616da99e99A");
        _addresses.put("5777", "0xc57BEAf4483f9cE81304146bA975984B7F61fBa0");
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

    public RemoteFunctionCall<Comment> getComment(BigInteger postID, BigInteger commentID) {
        final Function function = new Function(FUNC_GETCOMMENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(postID),
                        new org.web3j.abi.datatypes.generated.Uint256(commentID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Comment>() {}));
        return executeRemoteCallSingleValueReturn(function, Comment.class);
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

    public static class Comment extends DynamicStruct {
        public BigInteger commentID;

        public String commentAuthor;

        public String commentMessage;

        public Comment(BigInteger commentID, String commentAuthor, String commentMessage) {
            super(new org.web3j.abi.datatypes.generated.Uint256(commentID),new org.web3j.abi.datatypes.Utf8String(commentAuthor),new org.web3j.abi.datatypes.Utf8String(commentMessage));
            this.commentID = commentID;
            this.commentAuthor = commentAuthor;
            this.commentMessage = commentMessage;
        }

        public Comment(Uint256 commentID, Utf8String commentAuthor, Utf8String commentMessage) {
            super(commentID,commentAuthor,commentMessage);
            this.commentID = commentID.getValue();
            this.commentAuthor = commentAuthor.getValue();
            this.commentMessage = commentMessage.getValue();
        }
    }
}
