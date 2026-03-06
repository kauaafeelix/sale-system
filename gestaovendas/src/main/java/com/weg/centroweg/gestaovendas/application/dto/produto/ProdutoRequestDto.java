package com.weg.centroweg.gestaovendas.application.dto.produto;

import java.util.UUID;

public record ProdutoRequestDto(
        String nome,
        String descricao,
        double preco,
        int quantidadeEstoque
) {
}
