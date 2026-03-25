package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.ItemPedidoMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.ItemPedidoService;
import com.weg.centroweg.gestaovendas.domain.entity.ItemPedido;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import com.weg.centroweg.gestaovendas.domain.entity.Produto;
import com.weg.centroweg.gestaovendas.domain.repository.ItemPedidoRepository;
import com.weg.centroweg.gestaovendas.domain.repository.PedidoRepository;
import com.weg.centroweg.gestaovendas.domain.repository.ProdutoRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoRepository itemRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoMapper itemMapper;

    @Override
    public ItemPedidoResponseDto criarItemPedido(ItemPedidoRequestDto request) {

        Pedido pedido = pedidoRepository.findById(request.idPedido())
                .orElseThrow(() -> new BusinessException("O ID do Pedido não existe"));


        Produto produto = produtoRepository.findById(request.idProduto())
                .orElseThrow(() -> new BusinessException("O ID do Produto não existe"));

        ItemPedido item = itemMapper.toEntity(request);

        item.setPedido(pedido);
        item.setProduto(produto);

        itemRepository.save(item);

        return itemMapper.toDto(item);

    }

    @Override
    public ItemPedidoResponseDto buscarPorId(UUID id) {

        ItemPedido item = itemRepository.findById(id)
                .orElseThrow(() -> new BusinessException("O ID do ItemPedido não existe"));

        return itemMapper.toDto(item);
    }

    @Override
    public List<ItemPedidoResponseDto> listarItemPedidos() {

        return itemRepository.findAll()
                .stream()
                .map(itemMapper::toDto)
                .toList();

    }

    @Override
    public ItemPedidoResponseDto atualizarItemPedido(UUID id, ItemPedidoRequestDto request) {
        ItemPedido item = itemRepository.findById(id)
                .orElseThrow(() -> new BusinessException("O ID do ItemPedido não existe"));

        Pedido pedido = pedidoRepository.findById(request.idPedido())
                .orElseThrow(() -> new BusinessException("O ID do Pedido não existe"));

        Produto produto = produtoRepository.findById(request.idProduto())
                .orElseThrow(() -> new BusinessException("O ID do Produto não existe"));

        item.setQuantidade(request.quantidade());
        item.setPrecoUnitario(request.precoUnitario());
        item.setPedido(pedido);
        item.setProduto(produto);

        itemRepository.save(item);

        return itemMapper.toDto(item);
    }

    @Override
    public void deletarItemPedido(UUID id) {

        ItemPedido item = itemRepository.findById(id)
                .orElseThrow(() -> new BusinessException("O ID do ItemPedido não existe"));

        itemRepository.delete(item);
    }
}
