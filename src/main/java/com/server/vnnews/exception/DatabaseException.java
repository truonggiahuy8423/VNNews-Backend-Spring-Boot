package com.server.vnnews.exception;

public class DatabaseException extends RuntimeException{
    public static int NOT_FOUND = 998;
    public static int DATA_INTEGRITY_VIOLATION = 999;


    private int code;
    private String message;

    public DatabaseException(String message, int code) {
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
