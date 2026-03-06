package com.weg.centroweg.gestaovendas.web.advice;

public class ApiExceptionHandler extends RuntimeException {
    public ApiExceptionHandler(String message) {
        super(message);
    }
}
