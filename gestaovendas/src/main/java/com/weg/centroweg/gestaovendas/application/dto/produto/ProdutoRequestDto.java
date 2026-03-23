package com.weg.centroweg.gestaovendas.application.dto.produto;

public record ProdutoRequestDto(
        String nome,
        String descricao,
        double preco,
        int estoque
) {
}
