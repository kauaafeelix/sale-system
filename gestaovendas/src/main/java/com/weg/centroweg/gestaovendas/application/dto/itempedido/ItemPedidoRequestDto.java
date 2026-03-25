package com.weg.centroweg.gestaovendas.application.dto.itempedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record ItemPedidoRequestDto(
        @PositiveOrZero(message = "A quantidade não pode ser menor que 0.")
        @NotNull(message = "A quantidade é obrigatória")
        int quantidade,

        @PositiveOrZero(message = "O preço não pode ser menor que 0.")
        @NotNull(message = "O preço não pode ser nulo.")
        double precoUnitario,

        @Positive(message = "O ID do Pedido não pode ser um número negativo.")
        @NotNull(message = "O ID do Pedido não pode ser nulo.")
        UUID idPedido,

        @Positive(message = "O ID do Produto não pode ser um número negativo.")
        @NotNull(message = "O ID do Produto não pode ser nulo.")
        UUID idProduto
) {
}
