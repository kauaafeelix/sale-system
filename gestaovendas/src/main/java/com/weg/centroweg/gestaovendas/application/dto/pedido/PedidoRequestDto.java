package com.weg.centroweg.gestaovendas.application.dto.pedido;

import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoRequestDto(
        UUID clienteId,
        UUID usuarioId,
        LocalDateTime dataPedido,
        double valorTotal,
        StatusPedido status
) {
}
