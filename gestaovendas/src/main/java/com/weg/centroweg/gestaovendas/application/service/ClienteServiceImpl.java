package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteResponseDto;
<<<<<<< HEAD
import com.weg.centroweg.gestaovendas.application.service.contracts.ClienteService;
import lombok.RequiredArgsConstructor;
=======
import com.weg.centroweg.gestaovendas.application.mapper.ClienteMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.ClienteService;
import com.weg.centroweg.gestaovendas.domain.entity.Cliente;
import com.weg.centroweg.gestaovendas.domain.repository.ClienteRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
<<<<<<< HEAD
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    @Override
    public ClienteResponseDto criarCliente(ClienteRequestDto request) {
        return null;
=======
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
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public ClienteResponseDto buscarPorId(UUID id) {
<<<<<<< HEAD
        return null;
=======
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        return mapper.toDto(cliente);
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public List<ClienteResponseDto> listarClientes() {
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
    public ClienteResponseDto atualizarCliente(UUID id, ClienteRequestDto request) {
<<<<<<< HEAD
        return null;
=======
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());

        repository.save(cliente);

        return mapper.toDto(cliente);
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }

    @Override
    public void deletarCliente(UUID id) {
<<<<<<< HEAD

=======
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        repository.deleteById(id);
>>>>>>> 9492a955e81b0d9c84490e314405390af2e552d6
    }
}
