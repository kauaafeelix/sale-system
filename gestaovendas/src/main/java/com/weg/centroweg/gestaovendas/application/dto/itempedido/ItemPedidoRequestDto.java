package com.weg.centroweg.gestaovendas.application.dto.itempedido;

import java.util.UUID;

public record ItemPedidoRequestDto(
        int quantidade,
        double precoUnitario,
        UUID idPedido,
        UUID idProduto
) {
}
