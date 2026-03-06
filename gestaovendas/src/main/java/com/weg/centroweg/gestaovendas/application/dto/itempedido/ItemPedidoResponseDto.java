package com.weg.centroweg.gestaovendas.application.dto.itempedido;

public record ItemPedidoResponseDto(
        String nomeProduto,
        int quantidade,
        double precoUnitario
) {
}
