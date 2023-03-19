package com.example.springbatch.utils.exception;

/**
 * Exception common class for business exception.
 * 
 * @author example
 */
public class BusinessException extends AppException {

    /** Default serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor method.
     */
    public BusinessException() {
        super();
    }

    /**
     * Constructor method.
     * 
     * @param message error message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructor method.
     * 
     * @param message error message
     * @param args    message parameters
     */
    public BusinessException(String message, String... args) {
        super(message, args);
    }

}
