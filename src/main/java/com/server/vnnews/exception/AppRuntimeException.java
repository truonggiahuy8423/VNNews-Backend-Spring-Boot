package com.server.vnnews.exception;

public class AppRuntimeException extends RuntimeException {
    public static int AUTHENTICATION_FAILED = 1;

    private int code;
    private String message;

    public AppRuntimeException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
