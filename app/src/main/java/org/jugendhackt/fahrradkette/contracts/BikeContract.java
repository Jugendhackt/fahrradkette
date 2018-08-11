package org.jugendhackt.fahrradkette.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

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
    private static final String BINARY = "608060405234801561001057600080fd5b506040516101833803806101838339810160409081528151602080840151928401516060850151608086015160a087015160008054600160a060020a031916331790556001869055600287905560038490559187018051959793959094919093019261008291600491908601906100a5565b5081516100969060059060208501906100a5565b50600655506101409350505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100e657805160ff1916838001178555610113565b82800160010185558215610113579182015b828111156101135782518255916020019190600101906100f8565b5061011f929150610123565b5090565b61013d91905b8082111561011f5760008155600101610129565b90565b60358061014e6000396000f3006080604052600080fd00a165627a7a72305820ef29c3ea8fe5a111aac30415f316a2069768e7f26d760bacf7dffdf58b56737f0029";

    protected BikeContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BikeContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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

    public static BikeContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BikeContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BikeContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BikeContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
