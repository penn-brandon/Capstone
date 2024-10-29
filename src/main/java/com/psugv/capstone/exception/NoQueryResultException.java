package com.psugv.capstone.exception;

public class NoQueryResultException extends RuntimeException{

    @Override
    public String getMessage() {

        return "No query result found";
    }

    public NoQueryResultException(String message) {

        super(message);
    }
}
