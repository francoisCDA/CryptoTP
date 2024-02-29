package org.zythos.cryptoaddict_api.exception;

public class CustomerEmailOrPasswordIsWrong extends Exception{

    public CustomerEmailOrPasswordIsWrong(String email, String password) {
        super("you selected wrong " + email + "or wrong " + password);
    }
}
