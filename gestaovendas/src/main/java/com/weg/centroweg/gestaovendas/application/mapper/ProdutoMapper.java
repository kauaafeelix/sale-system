package com.weg.centroweg.gestaovendas.application.mapper;

import com.weg.centroweg.gestaovendas.application.dto.produto.request.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.response.ProdutoResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {


    public Produto toEntity(ProdutoRequestDto request){
        return new Produto(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.estoque()
        );
    }

    public ProdutoResponseDto toDto(Produto produto){

        return new ProdutoResponseDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoque()
        );
    }
}
