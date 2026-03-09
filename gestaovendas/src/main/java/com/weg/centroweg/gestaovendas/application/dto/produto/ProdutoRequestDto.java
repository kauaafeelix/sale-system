package com.weg.centroweg.gestaovendas.application.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record ProdutoRequestDto(

        @NotBlank (message = "O nome do produto é obrigatório")
        String nome,

        @NotBlank (message = "A descrição do produto é obrigatória")
        String descricao,

        @Positive (message = "O preço deve ser um número maior que zero")
        double preco,

        @PositiveOrZero (message = "A quantidade em estoque deve ser um número positivo ou zero")
        int quantidadeEstoque
) {
}
