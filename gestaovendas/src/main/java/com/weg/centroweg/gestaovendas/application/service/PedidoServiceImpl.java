package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
<<<<<<< HEAD
import com.weg.centroweg.gestaovendas.application.service.contracts.PedidoService;
import lombok.RequiredArgsConstructor;
=======
import com.weg.centroweg.gestaovendas.application.mapper.PedidoMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.PedidoService;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import com.weg.centroweg.gestaovendas.domain.repository.PedidoRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
<<<<<<< HEAD
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    @Override
    public PedidoResponseDto criarPedido(PedidoRequestDto request) {
        return null;
=======
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
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public PedidoResponseDto buscarPorId(UUID id) {
<<<<<<< HEAD
        return null;
=======

        Pedido pedido = repository.findById(id).orElseThrow(()-> new BusinessException("O Pedido não existe."));

        return mapper.toDto(pedido);
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public List<PedidoResponseDto> listarPedidosPorUsuario(UUID usuarioId) {
<<<<<<< HEAD
        return List.of();
=======
        return repository.findByIdUsuario(usuarioId)
                .stream()
                .map(mapper::toDto)
                .toList();
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public List<PedidoResponseDto> listarPedidos() {
<<<<<<< HEAD
        return List.of();
=======

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public List<PedidoResponseDto> listarPedidosPorCliente(UUID clienteId) {
<<<<<<< HEAD
        return List.of();
=======
        return repository.findByIdCliente(clienteId)
                .stream()
                .map(mapper::toDto)
                .toList();
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public void cancelarPedido(UUID id) {

<<<<<<< HEAD
=======
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

>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }
}
