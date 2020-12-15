package com.games.demo.exception;

public class WrongPasswordException extends Exception {
    public WrongPasswordException(String msg, Throwable t) {
        super(msg, t);
    }
    public WrongPasswordException(String msg) {
        super(msg);
    }
}

