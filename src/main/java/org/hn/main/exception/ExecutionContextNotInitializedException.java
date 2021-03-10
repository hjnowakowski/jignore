package org.hn.main.exception;

public class ExecutionContextNotInitializedException extends RuntimeException {
    public ExecutionContextNotInitializedException(String message) {
        super(message);
    }
}
