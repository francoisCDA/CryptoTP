package org.zythos.cryptodealer_api.exception;

public class CryptocurrencyNameExist extends Exception {
    public CryptocurrencyNameExist(String name){
        super(name + " is already use");
    }
}
