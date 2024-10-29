package com.psugv.capstone.exception;

public class InsertErrorException extends RuntimeException{

    @Override
    public String getMessage() {

        return "Insert to database failed";
    }

    public InsertErrorException(String message) {

        super(message);
    }
}
