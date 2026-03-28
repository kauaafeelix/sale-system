package com.weg.centroweg.gestaovendas.infra.exception;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String recurso, Object id) {
        super(recurso + " não encontrado com id: " + id);
    }
}
