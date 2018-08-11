package org.jugendhackt.fahrradkette;

import android.content.Context;
import android.content.SharedPreferences;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Wallet {

    private Context context;
    private SharedPreferences sharedPref;

    public Wallet(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void newWallet(String password) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException {
        String fileName = WalletUtils.generateNewWalletFile(
                password,
                context.getFilesDir(), false);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.wallet_file_name), fileName);
        editor.commit();
    }

    public Credentials loadWallet(String password) throws IOException, CipherException {
        String filename = sharedPref.getString(context.getString(R.string.wallet_file_name), "no_filename");
        Credentials credentials = WalletUtils.loadCredentials(
                password,
                new File(context.getFilesDir(), filename));
        return credentials;
    }
}
