package com.example.springbatch.utils.exception;

/**
 * Exception common class for validation exception.
 * 
 * @author example
 */
public class ValidException extends AppException {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor method.
     */
    public ValidException() {
        super();
    }

    /**
     * Constructor method.
     * 
     * @param message error message
     */
    public ValidException(String message) {
        super(message);
    }

    /**
     * Constructor method.
     * 
     * @param message error message
     * @param args    message parameters
     */
    public ValidException(String message, String... args) {
        super(message, args);
    }

}
