package com.project.my.exception;

public class InternalException extends RuntimeException {


    public InternalException() {
    }

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Exception e) {
        super(message, e.getCause());
    }

}
