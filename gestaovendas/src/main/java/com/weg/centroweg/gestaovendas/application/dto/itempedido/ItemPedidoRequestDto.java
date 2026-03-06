package com.weg.centroweg.gestaovendas.application.dto.itempedido;

public record ItemPedidoRequestDto(
        Long produtoId,
        int quantidade
) {
}
