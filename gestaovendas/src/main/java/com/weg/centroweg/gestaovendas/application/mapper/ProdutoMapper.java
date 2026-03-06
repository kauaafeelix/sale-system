package com.weg.centroweg.gestaovendas.application.mapper;

import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity (ProdutoRequestDto dto){
        return new Produto(
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.quantidadeEstoque()
        );
    }

    public ProdutoResponseDto toDto (Produto produto){
        return new ProdutoResponseDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidadeEstoque()
        );
    }

}
