package com.weg.centroweg.gestaovendas.application.dto.produto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AtualizaEstoqueRequestDto(

        @NotNull(message = "A quantidade é obrigatória")
        @PositiveOrZero(message = "A quantidade não pode ser negativa")
        int estoque
) {
}
