package com.example.springbatch.utils.exception;

import java.text.MessageFormat;

/**
 * Exception common class which base exception class.
 * 
 * @author example
 */
public class AppException extends Exception {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Error code. */
    protected String errorCode;

    /** Throwed Exception. */
    private Exception throwError;

    /**
     * Constructor method.
     */
    public AppException() {
        super();
    }

    /**
     * Constructor method.
     * 
     * @param message error message
     */
    public AppException(String message) {
        super(message);
    }

    /**
     * Constructor method.
     * 
     * @param message error message
     * @param args    message parameters
     */
    public AppException(String message, String... args) {
        super(MessageFormat.format(message, (Object) args));
    }

    /**
     * Constructor method.
     * 
     * @param message error message
     */
    public AppException(Exception e, String message) {
        super(message);
        this.throwError = e;
    }

    /**
     * Constructor method.
     * 
     * @param message error message
     * @param args    message parameters
     */
    public AppException(Exception e, String message, String... args) {
        super(MessageFormat.format(message, (Object) args));
        this.throwError = e;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Exception getThrowError() {
        return throwError;
    }

    public void setThrowError(Exception throwError) {
        this.throwError = throwError;
    }
}
