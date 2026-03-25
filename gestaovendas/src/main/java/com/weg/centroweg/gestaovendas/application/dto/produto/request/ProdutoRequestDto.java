package com.weg.centroweg.gestaovendas.application.dto.produto.request;

public record ProdutoRequestDto(
        String nome,
        String descricao,
        double preco,
        int estoque
) {
}
