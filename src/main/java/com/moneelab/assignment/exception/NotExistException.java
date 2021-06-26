package com.moneelab.assignment.exception;

public class NotExistException extends IllegalArgumentException {
    public NotExistException(String message) {
        super(message);
    }
}
