package com.product.excepcion;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    // Getters y Setters
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
