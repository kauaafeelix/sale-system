package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.itempedido.ItemPedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.PedidoService;
import com.weg.centroweg.gestaovendas.application.mapper.PedidoMapper;
import com.weg.centroweg.gestaovendas.domain.entity.ItemPedido;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import com.weg.centroweg.gestaovendas.domain.entity.Produto;
import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import com.weg.centroweg.gestaovendas.domain.repository.PedidoRepository;
import com.weg.centroweg.gestaovendas.domain.repository.ProdutoRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoMapper mapper;
    private final PedidoRepository repository;
    private final ProdutoRepository produtoRepository;


    @Override
    public PedidoResponseDto criarPedido(PedidoRequestDto request) {
        Pedido pedido = new Pedido();

        pedido.setClienteId(request.clienteId());
        pedido.setUsuarioId(request.usuarioId());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PENDENTE);

        List<ItemPedido> itens = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedidoRequestDto itemDto : request.itens()) {

            Produto produto = produtoRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new BusinessException("Produto não encontrado"));

            if (produto.getQuantidadeEstoque() < itemDto.quantidade()) {
                throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            ItemPedido item = new ItemPedido();

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDto.quantidade());
            item.setPrecoUnitario(produto.getPreco());

            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() - itemDto.quantidade()
            );
            produtoRepository.save(produto);

            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDto.quantidade()));
            total = total.add(subtotal);

            itens.add(item);
        }

        pedido.setItens(itens);
        pedido.setValorTotal(total.doubleValue());

        repository.save(pedido);

        return mapper.toDto(pedido);

    }

    @Override
    public PedidoResponseDto buscarPorId(UUID id) {

        Pedido pedido = repository.findById(id).orElseThrow(()-> new BusinessException("O Pedido não existe."));

        return mapper.toDto(pedido);
    }

    @Override
    public List<PedidoResponseDto> listarPedidosPorUsuario(UUID usuarioId) {

        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<PedidoResponseDto> listarPedidos() {


        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<PedidoResponseDto> listarPedidosPorCliente(UUID clienteId) {

        return repository.findByClienteId(clienteId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void cancelarPedido(UUID id) {


        Pedido pedido = repository.findById(id).
                orElseThrow(()-> new BusinessException("O Pedido não existe."));

        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new BusinessException("Pedido já está cancelado");
        }

        if (pedido.getStatus() == StatusPedido.ENVIADO) {
            throw new BusinessException("Pedido enviado não pode ser cancelado");
        }

        pedido.setStatus(StatusPedido.CANCELADO);

        repository.save(pedido);

    }
}
