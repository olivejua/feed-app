package com.moneelab.assignment.exception;

public class WrongLoginInputException extends IllegalArgumentException {

    public WrongLoginInputException(String message) {
        super(message);
    }
}
