package org.hn.main.exception;

public class ResourceFileNotFoundException extends RuntimeException {
    public ResourceFileNotFoundException(String message) {
        super(message);
    }
}
