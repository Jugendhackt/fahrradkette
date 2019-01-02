package org.jugendhackt.fahrradkette;

import android.util.Log;

import org.jugendhackt.fahrradkette.contracts.BikeContract;
import org.web3j.abi.EventEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rx.functions.Action1;

public class EthereumThread extends Thread {

    private Credentials wallet;

    public EthereumThread(Credentials wallet) {
        this.wallet = wallet;
    }

    public void run() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        //HttpService hs = new HttpService("https://rinkeby.infura.io/v3/1e828833065f4455a2f04805cf1c0358", builder.build(), false);
        HttpService hs = new HttpService("http://tarf.ddns.net:8545", builder.build(), true);
        //HttpService hs = new HttpService("http://192.168.21.130:8545", builder.build(), true);
        Web3j web3 = Web3jFactory.build(hs);

        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3.web3ClientVersion().send();
            Log.d(MainActivity.TAG, wallet.getAddress());

            EthGetBalance balance = web3.ethGetBalance(wallet.getAddress(),
                    DefaultBlockParameterName.LATEST).sendAsync().get();
            Log.d(MainActivity.TAG, "Balance:" + balance.getBalance().toString());

            /*
            EthTransaction transaction = web3.ethGetTransactionByHash("0xded7a2ff08da4e9aaf62f2d9349f716ecf377ef31db67141a1e0c78d9536f226")
                    .sendAsync().get();
            Log.d(MainActivity.TAG, "From: " + transaction.getResult().getFrom());
            */
            BigInteger blockNum = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send()
                    .getBlock().getNumber();

            Log.d(MainActivity.TAG, "Latest block num:" + blockNum.toString());

            Bike bike = new Bike(11.12, 50.1, 10, 235, "Mein Fahrrad 2",
                    "Besonderheiten");
            /*
            BikeContract b = bike.getContractRemoteCall(web3, wallet).sendAsync().get();
            Log.d(MainActivity.TAG, b.getContractAddress());*/

            /*
            String encodedEventSignature = EventEncoder.encode(b.NEWBIKE_EVENT);

            EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
                    DefaultBlockParameterName.LATEST, b.getContractAddress().substring(2));
            filter.addSingleTopic(encodedEventSignature);

            web3.ethLogObservable(filter).subscribe(new Action1<org.web3j.protocol.core.methods.response.Log>() {
                @Override
                public void call(org.web3j.protocol.core.methods.response.Log log) {
                    Log.d(MainActivity.TAG, "Found event: " + log.getTopics().toString());
                }
            });
            */
            //Log.d(MainActivity.TAG, String.valueOf(b.isValid()));


        } catch (IOException e) {
            Log.e(MainActivity.TAG, "error111");
            e.printStackTrace();
        } catch (InterruptedException e) {
            Log.e(MainActivity.TAG, "error111");
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.e(MainActivity.TAG, "error111");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        Log.d(MainActivity.TAG, clientVersion);
    }
}
