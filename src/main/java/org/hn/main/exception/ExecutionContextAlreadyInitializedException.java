package org.hn.main.exception;

public class ExecutionContextAlreadyInitializedException extends RuntimeException {
    public ExecutionContextAlreadyInitializedException(String message) {
        super(message);
    }
}
