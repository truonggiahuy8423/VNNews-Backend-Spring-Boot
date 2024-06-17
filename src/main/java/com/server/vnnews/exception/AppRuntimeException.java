package com.server.vnnews.exception;

public class AppRuntimeException extends RuntimeException {
    public static int AUTHENTICATION_FAILED = 1;
    public static String AUTHENTICATION_FAILED_MESSAGE = "Incorrect email or password!";
    public static int PERMISSION_DENIED = 2;
    public static String PERMISSION_DENIED_MESSAGE = "Permission denied";
    public static int UNKNOWN_ERROR= 3;
    public static String UNKNOWN_ERROR_MESSAGE = "Unknown error";


    public static int EMAIL_EXISTED= 4;
    public static String EMAIL_EXISTED_MESSAGE = "Email đã đăng ký tài khoản khác";

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
