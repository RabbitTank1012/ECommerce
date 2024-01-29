package com.dbn.onlineshopping.exception;

public class UserSaveFailedException extends RuntimeException{
    public UserSaveFailedException(String message){
        super(String.format(message));

    }
}
