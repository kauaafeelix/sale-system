package com.weg.centroweg.gestaovendas.application.dto.pedido;

public record PedidoResponseDto(
        Long id,
        String clienteNome,
        String clienteEmail,
        String clienteTelefone,
        String dataPedido,
        double valorTotal
) {
}
