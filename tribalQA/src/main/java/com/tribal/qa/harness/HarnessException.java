package com.tribal.qa.harness;

public class HarnessException extends RuntimeException {

    public HarnessException(String message) {
        super(message);
    }

    public HarnessException(String message, Throwable t) {
        super(message, t);
    }
}
