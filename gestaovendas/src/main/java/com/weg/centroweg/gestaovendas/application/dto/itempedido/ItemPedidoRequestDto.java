package com.weg.centroweg.gestaovendas.application.dto.itempedido;

import java.util.UUID;

public record ItemPedidoRequestDto(
        UUID pedidoId,
        UUID produtoId,
        int quantidade,
        double precoUnitario
) {
}
