package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.PedidoService;
import com.weg.centroweg.gestaovendas.application.mapper.PedidoMapper;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import com.weg.centroweg.gestaovendas.domain.repository.PedidoRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoMapper mapper;
    private final PedidoRepository repository;

    public PedidoServiceImpl(PedidoMapper mapper, PedidoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public PedidoResponseDto criarPedido(PedidoRequestDto request) {
        Pedido pedido = mapper.toEntity(request);

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
