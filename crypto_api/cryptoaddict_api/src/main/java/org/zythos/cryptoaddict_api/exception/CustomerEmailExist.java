package org.zythos.cryptoaddict_api.exception;

public class CustomerEmailExist extends Exception{
    public CustomerEmailExist(String email) { super(email + " is already used !! ");}
}
