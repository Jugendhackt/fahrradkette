package org.jugendhackt.fahrradkette;

import android.util.Log;

import org.jugendhackt.fahrradkette.contracts.BikeContract;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class EthereumThread extends Thread {

    private Credentials wallet;

    private static final BigInteger GAS_PRICE = Contract.GAS_PRICE;
    private static final BigInteger GAS_LIMIT = Contract.GAS_LIMIT;

    public EthereumThread(Credentials wallet) {
        this.wallet = wallet;
    }

    public void run() {
        //Web3j web3 = Web3jFactory.build(new HttpService("http://192.168.21.165:8545"));
        Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/v3/1e828833065f4455a2f04805cf1c0358"));
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3.web3ClientVersion().send();
            EthGetBalance balance = web3.ethGetBalance(wallet.getAddress(),
                    DefaultBlockParameterName.LATEST).sendAsync().get();
            Log.d(MainActivity.TAG, "Balance:" + balance.getBalance().toString());

            EthTransaction transaction = web3.ethGetTransactionByHash("0xded7a2ff08da4e9aaf62f2d9349f716ecf377ef31db67141a1e0c78d9536f226")
                    .sendAsync().get();
            Log.d(MainActivity.TAG, "From: " + transaction.getResult().getFrom());

            BigInteger blockNum = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send()
                    .getBlock().getNumber();

            Log.d(MainActivity.TAG, "Latest block num:" + blockNum.toString());

            Bike bike = new Bike(11.12, 50.1, 10, 235, "Mein Fahrrad",
                    "Besonderheiten");

            BikeContract b = BikeContract.deploy(web3, this.wallet, GAS_PRICE, GAS_LIMIT,
                    bike.getPrice(), bike.getLatitude(),
                    bike.getLongitude(), bike.name, bike.specialities, bike.getCode())
                    .send();

            Log.d(MainActivity.TAG, String.valueOf(b.isValid()));


        } catch (IOException e) {
            Log.e(MainActivity.TAG, "error");
            e.printStackTrace();
        } catch (InterruptedException e) {
            Log.e(MainActivity.TAG, "error");
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.e(MainActivity.TAG, "error");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        Log.d(MainActivity.TAG, clientVersion);
    }
}
