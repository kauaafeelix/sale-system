package com.weg.centroweg.gestaovendas.application.dto.produto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoRequestDto(

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @PositiveOrZero(message = "O preço não pode ser menor que 0.")
        @NotNull(message = "O preço não pode ser nulo.")
        double preco,

        @PositiveOrZero(message = "O estoque não pode ser menor que 0.")
        @NotNull(message = "O estoque não pode ser nulo.")
        int estoque
) {
}
