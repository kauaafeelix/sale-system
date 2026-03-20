package com.weg.centroweg.gestaovendas.application.dto.itempedido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ItemPedidoRequestDto(

        @NotNull (message = "O ID do produto é obrigatório")
        UUID produtoId,

        @Positive (message = "A quantidade deve ser um número maior que zero")
        int quantidade

) {
}
