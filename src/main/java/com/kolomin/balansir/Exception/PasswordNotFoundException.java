package com.kolomin.balansir.Exception;

public class PasswordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6889651058820454336L;

    public PasswordNotFoundException() {
    }

    public PasswordNotFoundException(String message) {
        super(message);
    }

    public PasswordNotFoundException(Throwable cause) {
        super(cause);
    }

    public PasswordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
