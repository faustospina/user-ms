package com.bci.cl.user_ms.exception;

import lombok.Builder;

@Builder
public class BusinessException extends Exception{
    private String message;
    // Constructor sin argumentos
    public BusinessException() {
        super();
    }

    // Constructor que recibe un mensaje
    public BusinessException(String message) {
        super(message);
        this.message=message;
    }

    // Constructor que recibe un mensaje y una causa
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor que recibe una causa
    public BusinessException(Throwable cause) {
        super(cause);
    }
}
