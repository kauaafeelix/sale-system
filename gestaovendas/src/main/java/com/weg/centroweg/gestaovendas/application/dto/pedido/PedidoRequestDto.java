package com.weg.centroweg.gestaovendas.application.dto.pedido;

import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoRequestDto(

        @FutureOrPresent(message = "A data do pedido não pode ser uma data passada")
        LocalDateTime dataPedido,

        @PositiveOrZero(message = "O valor total não pode ser menor que 0.")
        @NotNull(message = "O valor total não pode ser nulo.")
        double valorTotal,

        @NotNull(message = "O status não pode ser nulo.")
        StatusPedido status,

        @Positive(message = "O ID do Usuário não pode ser um número negativo.")
        @NotNull(message = "O ID do Usuário não pode ser nulo.")
        UUID idUsuario,

        @Positive(message = "O ID do Cliente não pode ser um número negativo.")
        @NotNull(message = "O ID do Cliente não pode ser nulo.")
        UUID idCliente
) {
}
