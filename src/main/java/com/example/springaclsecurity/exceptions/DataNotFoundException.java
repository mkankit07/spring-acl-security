package com.example.springaclsecurity.exceptions;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(final String message) {
        super(message);
    }
}
