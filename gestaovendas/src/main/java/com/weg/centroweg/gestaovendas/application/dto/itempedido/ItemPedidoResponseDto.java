package com.weg.centroweg.gestaovendas.application.dto.itempedido;

import java.util.UUID;

public record ItemPedidoResponseDto(
        UUID produtoId,
        String nomeProduto,
        int quantidade,
        double precoUnitario
) {
}
