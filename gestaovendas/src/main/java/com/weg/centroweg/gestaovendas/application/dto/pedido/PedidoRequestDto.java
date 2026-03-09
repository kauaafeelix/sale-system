package com.weg.centroweg.gestaovendas.application.dto.pedido;

import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoRequestDto(

        @NotNull (message = "O ID do cliente é obrigatório")
        UUID clienteId,

        @NotNull (message = "O ID do usuário é obrigatório")
        UUID usuarioId,

        @PastOrPresent (message = "A data do pedido não pode ser no futuro")
        LocalDateTime dataPedido,

        @Positive (message = "O valor total deve ser um número maior que zero")
        double valorTotal,

        @NotNull(message = "O status do pedido é obrigatório")
        StatusPedido status
) {
}
