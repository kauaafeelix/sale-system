package com.weg.centroweg.gestaovendas.application.service.contracts;

import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoResponseDto;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoService {
    ItemPedidoResponseDto criarItemPedido(ItemPedidoRequestDto request);

    ItemPedidoResponseDto buscarPorId(UUID id);

    List<ItemPedidoResponseDto> listarItemPedidos();

    ItemPedidoResponseDto atualizarItemPedido(UUID id, ItemPedidoRequestDto request);

    void deletarItemPedido(UUID id);
}
