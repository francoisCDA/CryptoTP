package org.zythos.cryptodealer_api.exception;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String cryptoName) {
        super(cryptoName + " out of stock");
    }

}
