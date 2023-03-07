package com.example.naijavotingsystem.exception;

public class EmailAlreadyExistException extends Exception{
    public EmailAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
