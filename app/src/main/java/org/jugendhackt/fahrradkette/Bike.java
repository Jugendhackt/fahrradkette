package org.jugendhackt.fahrradkette;


import org.jugendhackt.fahrradkette.contracts.BikeContract;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.crypto.Credentials;

import java.math.BigInteger;

public class Bike {

    public double longitude;
    public double latitude;
    public long price;
    public int code;
    public String name;
    public String specialities;

    public Bike(double longitude, double latitude, long price, int code, String name, String specialities) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.price = price;
        this.code = code;
        this.name = name;
        this.specialities = specialities;
    }

    public BigInteger getLongitude() {
        long rounded = Math.round(this.longitude * 100000);
        return BigInteger.valueOf(rounded);
    }

    public BigInteger getLatitude() {
        long rounded = Math.round(this.latitude * 100000);
        return BigInteger.valueOf(rounded);
    }

    public BigInteger getCode() {
        return BigInteger.valueOf(code);
    }

    public BigInteger getPrice() {
        return BigInteger.valueOf(price);
    }

    public RemoteCall<BikeContract> getContractRemoteCall(Web3j web3, Credentials wallet) {
        return BikeContract.deploy(web3, wallet, Contract.GAS_PRICE, Contract.GAS_LIMIT,
                this.getPrice(), this.getLatitude(),
                this.getLongitude(), this.name, this.specialities, this.getCode());
    }
}
