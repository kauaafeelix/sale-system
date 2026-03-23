package com.weg.centroweg.gestaovendas.application.mapper;


import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoRequestDto request){

        return new Pedido (
                request.dataPedido(),
                request.valorTotal(),
                request.status()
        );
    }

    public PedidoResponseDto toDto(Pedido pedido){

        return new PedidoResponseDto(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getUsuario().getId(),
                pedido.getCliente().getId()
        );
    }

}
