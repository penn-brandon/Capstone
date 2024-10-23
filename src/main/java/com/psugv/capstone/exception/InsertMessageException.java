package com.psugv.capstone.exception;

public class InsertMessageException extends RuntimeException{

    @Override
    public String getMessage() {

        return "Insert message failed";
    }

    public InsertMessageException(String message) {

        super(message);
    }
}
