package com.weg.centroweg.gestaovendas.application.mapper;


import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.ItemPedido;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {

    public ItemPedido toEntity (ItemPedidoRequestDto request){

        return new ItemPedido(
                request.quantidade(),
                request.precoUnitario()
        );
    }

    public ItemPedidoResponseDto toDto(ItemPedido item){

        return new ItemPedidoResponseDto(
                item.getId(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getPedido().getId(),
                item.getProduto().getId()
        );
    }
}
