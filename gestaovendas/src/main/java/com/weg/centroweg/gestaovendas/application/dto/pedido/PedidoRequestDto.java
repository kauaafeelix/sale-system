package com.weg.centroweg.gestaovendas.application.dto.pedido;

import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoRequestDto;
import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoRequestDto(

        @NotNull (message = "O ID do cliente é obrigatório")
        UUID clienteId,
        UUID usuarioId,
        List<ItemPedidoRequestDto> itens
) {
}
