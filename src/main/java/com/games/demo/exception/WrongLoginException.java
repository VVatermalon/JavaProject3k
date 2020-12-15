package com.games.demo.exception;

public class WrongLoginException extends Exception {
    public WrongLoginException(String msg, Throwable t) {
        super(msg, t);
    }
    public WrongLoginException(String msg) {
        super(msg);
    }
}
