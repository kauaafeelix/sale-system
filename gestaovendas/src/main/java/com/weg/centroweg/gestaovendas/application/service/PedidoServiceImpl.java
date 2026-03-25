package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.pedido.PedidoResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.PedidoMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.PedidoService;
import com.weg.centroweg.gestaovendas.domain.entity.Cliente;
import com.weg.centroweg.gestaovendas.domain.entity.Pedido;
import com.weg.centroweg.gestaovendas.domain.entity.Usuario;
import com.weg.centroweg.gestaovendas.domain.entity.enums.StatusPedido;
import com.weg.centroweg.gestaovendas.domain.repository.ClienteRepository;
import com.weg.centroweg.gestaovendas.domain.repository.PedidoRepository;
import com.weg.centroweg.gestaovendas.domain.repository.UsuarioRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoMapper mapper;
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;


    @Override
    public PedidoResponseDto criarPedido(PedidoRequestDto request) {

        Usuario usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(()-> new BusinessException("O ID do usuário não existe"));
        Cliente cliente = clienteRepository.findById(request.idCliente())
                .orElseThrow(() -> new BusinessException("O ID do cliente não existe"));

        Pedido pedido = mapper.toEntity(request);

        pedido.setUsuario(usuario);
        pedido.setCliente(cliente);

        pedidoRepository.save(pedido);

        return mapper.toDto(pedido);
    }

    @Override
    public PedidoResponseDto buscarPorId(UUID id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(()-> new BusinessException("O ID do Pedido não existe"));

        return mapper.toDto(pedido);
    }

    @Override
    public List<PedidoResponseDto> listarPedidosPorUsuario(UUID usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new BusinessException("O ID do usuário não existe"));

        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);

        return pedidos
                .stream()
                .map(mapper::toDto)
                .toList();

    }

    @Override
    public List<PedidoResponseDto> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<PedidoResponseDto> listarPedidosPorCliente(UUID clienteId) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(()-> new BusinessException("O ID do cliente não existe."));

        return pedidoRepository.findByClienteId(clienteId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void cancelarPedido(UUID id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(()-> new BusinessException("O ID do Pedido não existe"));

        if (!pedido.getStatus().equals(StatusPedido.PENDENTE)) {
            throw new BusinessException("Apenas pedidos pendentes podem ser cancelados");
        }

        pedido.setStatus(StatusPedido.CANCELADO);

        pedidoRepository.save(pedido);
    }
}
