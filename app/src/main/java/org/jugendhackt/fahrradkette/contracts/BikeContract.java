package org.jugendhackt.fahrradkette.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class BikeContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506040516104973803806104978339810160409081528151602080840151928401516060850151608086015160a087015160008054600160a060020a03191633179055600186905560028790556003849055918701805195979395909491909301926100829160049190860190610100565b508151610096906005906020850190610100565b506006819055604080517f666168727261646b6574746500000000000000000000000000000000000000008152905190819003600c018120907f1b6f727afca82c1def6185bdc8c4a54db72a5f2ce0a4b8f630556346f85bacff90600090a250505050505061019b565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061014157805160ff191683800117855561016e565b8280016001018555821561016e579182015b8281111561016e578251825591602001919060010190610153565b5061017a92915061017e565b5090565b61019891905b8082111561017a5760008155600101610184565b90565b6102ed806101aa6000396000f3006080604052600436106100775763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde03811461007c5780634fd7d76a14610106578063589af69c1461012d5780638da5cb5b14610142578063a035b1fe14610180578063beb4319e14610195575b600080fd5b34801561008857600080fd5b506100916101aa565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100cb5781810151838201526020016100b3565b50505050905090810190601f1680156100f85780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561011257600080fd5b5061011b610238565b60408051918252519081900360200190f35b34801561013957600080fd5b5061011b61023e565b34801561014e57600080fd5b50610157610244565b6040805173ffffffffffffffffffffffffffffffffffffffff9092168252519081900360200190f35b34801561018c57600080fd5b5061011b610260565b3480156101a157600080fd5b50610091610266565b6004805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156102305780601f1061020557610100808354040283529160200191610230565b820191906000526020600020905b81548152906001019060200180831161021357829003601f168201915b505050505081565b60025481565b60035481565b60005473ffffffffffffffffffffffffffffffffffffffff1681565b60015481565b6005805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156102305780601f10610205576101008083540402835291602001916102305600a165627a7a723058209363b75ed84def57f9e116620e5049c8bb61284141188c101503633a22cbeed30029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_LATITUDE = "latitude";

    public static final String FUNC_LONGITUDE = "longitude";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_SPECIALITIES = "specialities";

    public static final Event NEWBIKE_EVENT = new Event("NewBike", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}),
            Arrays.<TypeReference<?>>asList());
    ;

    protected BikeContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BikeContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> latitude() {
        final Function function = new Function(FUNC_LATITUDE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> longitude() {
        final Function function = new Function(FUNC_LONGITUDE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> price() {
        final Function function = new Function(FUNC_PRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> specialities() {
        final Function function = new Function(FUNC_SPECIALITIES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<BikeContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _price, BigInteger _latitude, BigInteger _longitude, String _name, String _specialities, BigInteger _code) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_latitude), 
                new org.web3j.abi.datatypes.generated.Uint256(_longitude), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_specialities), 
                new org.web3j.abi.datatypes.generated.Uint256(_code)));
        return deployRemoteCall(BikeContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<BikeContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _price, BigInteger _latitude, BigInteger _longitude, String _name, String _specialities, BigInteger _code) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_latitude), 
                new org.web3j.abi.datatypes.generated.Uint256(_longitude), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_specialities), 
                new org.web3j.abi.datatypes.generated.Uint256(_code)));
        return deployRemoteCall(BikeContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<NewBikeEventResponse> getNewBikeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWBIKE_EVENT, transactionReceipt);
        ArrayList<NewBikeEventResponse> responses = new ArrayList<NewBikeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewBikeEventResponse typedResponse = new NewBikeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.topic = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewBikeEventResponse> newBikeEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewBikeEventResponse>() {
            @Override
            public NewBikeEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWBIKE_EVENT, log);
                NewBikeEventResponse typedResponse = new NewBikeEventResponse();
                typedResponse.log = log;
                typedResponse.topic = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewBikeEventResponse> newBikeEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWBIKE_EVENT));
        return newBikeEventObservable(filter);
    }

    public static BikeContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BikeContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BikeContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BikeContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class NewBikeEventResponse {
        public Log log;

        public byte[] topic;
    }
}
