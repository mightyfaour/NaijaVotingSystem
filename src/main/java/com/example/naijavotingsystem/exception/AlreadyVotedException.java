package com.example.naijavotingsystem.exception;

public class AlreadyVotedException extends Exception{
    public AlreadyVotedException(String errorMessage) {
        super(errorMessage);
    }
}
