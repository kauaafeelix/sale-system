package com.weg.centroweg.gestaovendas.infra.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(int estoqueAtual, int quantidadeSolicitada) {
        super("Estoque insuficiente. Disponível: " + estoqueAtual +
                ", solicitado: " + quantidadeSolicitada + ".");
    }
}
