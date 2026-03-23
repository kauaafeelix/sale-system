package com.weg.centroweg.gestaovendas.application.dto.produto;

import java.util.UUID;

public record ProdutoResponseDto(

        UUID id,
        String nome,
        String descricao,
        double preco,
        int quantidadeEstoque
) {
}
