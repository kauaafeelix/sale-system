package com.weg.centroweg.gestaovendas.infra.exception;

import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;

public class PedidoStatusInvalidoException extends RuntimeException {
    public PedidoStatusInvalidoException(StatusPedido statusAtual) {
        super("Operação inválida para pedido com status: " + statusAtual +
                ". Apenas pedidos com status PENDENTE podem ser cancelados.");
    }
}
