package com.games.demo.exception;

public class UserExistsException extends Exception {
    public UserExistsException(String msg, Throwable t) {
        super(msg, t);
    }
    public UserExistsException(String msg) {
        super(msg);
    }
}
