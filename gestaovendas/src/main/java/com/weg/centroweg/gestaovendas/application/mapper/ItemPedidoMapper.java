package com.weg.centroweg.gestaovendas.application.mapper;


import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.ItemPedido;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {

    public ItemPedido toEntity(ItemPedidoRequestDto dto){
        return new ItemPedido(
                dto.pedidoId(),
                dto.produtoId(),
                dto.quantidade(),
                dto.precoUnitario()
                );
    }

    public ItemPedidoResponseDto toDto ( ItemPedido entity){
        return new ItemPedidoResponseDto(
                entity.getId(),
                entity.getPedidoId(),
                entity.getProdutoId(),
                entity.getQuantidade(),
                entity.getPrecoUnitario()
        );
    }
}
