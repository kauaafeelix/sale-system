package com.weg.centroweg.gestaovendas.application.service.contracts;

import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoService {

    PedidoResponseDto criarPedido(PedidoRequestDto request);

    PedidoResponseDto buscarPorId(UUID id);

    List<PedidoResponseDto> listarPedidosPorUsuario(UUID usuarioId);

    List<PedidoResponseDto> listarPedidos();

    List<PedidoResponseDto> listarPedidosPorCliente(UUID clienteId);

    void cancelarPedido(UUID id);

}
