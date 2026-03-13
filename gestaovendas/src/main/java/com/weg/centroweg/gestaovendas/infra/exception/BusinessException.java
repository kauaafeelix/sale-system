package com.weg.centroweg.gestaovendas.infra.exception;


public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
