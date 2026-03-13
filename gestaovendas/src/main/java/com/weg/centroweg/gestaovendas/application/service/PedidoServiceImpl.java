package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    @Override
    public PedidoResponseDto criarPedido(PedidoRequestDto request) {
        return null;
    }

    @Override
    public PedidoResponseDto buscarPorId(UUID id) {
        return null;
    }

    @Override
    public List<PedidoResponseDto> listarPedidosPorUsuario(UUID usuarioId) {
        return List.of();
    }

    @Override
    public List<PedidoResponseDto> listarPedidos() {
        return List.of();
    }

    @Override
    public List<PedidoResponseDto> listarPedidosPorCliente(UUID clienteId) {
        return List.of();
    }

    @Override
    public void cancelarPedido(UUID id) {

    }
}
