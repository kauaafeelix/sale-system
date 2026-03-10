package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.ClienteMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.ClienteService;
import com.weg.centroweg.gestaovendas.domain.entity.Cliente;
import com.weg.centroweg.gestaovendas.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteServiceImpl(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClienteResponseDto criarCliente(ClienteRequestDto request) {
        Cliente cliente = mapper.toEntity(request);
        repository.save(cliente);

        return mapper.toDto(cliente);
    }

    @Override
    public ClienteResponseDto buscarPorId(UUID id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return mapper.toDto(cliente);
    }

    @Override
    public List<ClienteResponseDto> listarClientes() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ClienteResponseDto atualizarCliente(UUID id, ClienteRequestDto request) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());

        repository.save(cliente);

        return mapper.toDto(cliente);
    }

    @Override
    public void deletarCliente(UUID id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        repository.deleteById(id);
    }
}
