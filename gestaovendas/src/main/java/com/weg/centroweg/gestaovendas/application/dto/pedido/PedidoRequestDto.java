package com.weg.centroweg.gestaovendas.application.dto.pedido;

public record PedidoRequestDto(
        Long clienteId,
        String dataPedido,
        double valorTotal
) {
}
