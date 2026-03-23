package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.ItemPedidoService;

import java.util.List;
import java.util.UUID;

public class ItemPedidoServiceImpl implements ItemPedidoService {
    @Override
    public ItemPedidoResponseDto criarItemPedido(ItemPedidoRequestDto request) {
        return null;
    }

    @Override
    public ItemPedidoResponseDto buscarPorId(UUID id) {
        return null;
    }

    @Override
    public List<ItemPedidoResponseDto> listarItemPedidos() {
        return List.of();
    }

    @Override
    public ItemPedidoResponseDto atualizarItemPedido(UUID id, ItemPedidoRequestDto request) {
        return null;
    }

    @Override
    public void deletarItemPedido(UUID id) {

    }
}
