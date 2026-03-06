package com.weg.centroweg.gestaovendas.application.mapper;


import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {


    public Pedido toEntity(PedidoRequestDto dto) {
        return new Pedido(
                dto.clienteId(),
                dto.usuarioId(),
                dto.dataPedido(),
                dto.valorTotal(),
                dto.status()
        );
    }

    public PedidoResponseDto toDto(Pedido pedido) {
        return new PedidoResponseDto(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getUsuarioId(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                pedido.getStatus()
        );
    }
}
