package com.weg.centroweg.gestaovendas.application.dto.itempedido;

import java.util.UUID;

public record ItemPedidoResponseDto(
        UUID id,
        int quantidade,
        double precoUnitario,
        UUID idPedido,
        UUID idProduto
) {
}
