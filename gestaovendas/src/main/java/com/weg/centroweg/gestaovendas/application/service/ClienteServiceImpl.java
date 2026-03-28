package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.ClienteMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.ClienteService;
import com.weg.centroweg.gestaovendas.domain.entity.Cliente;
import com.weg.centroweg.gestaovendas.domain.repository.ClienteRepository;
import com.weg.centroweg.gestaovendas.infra.exception.EmailJaCadastradoException;
import com.weg.centroweg.gestaovendas.infra.exception.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Override
    public ClienteResponseDto criarCliente(ClienteRequestDto request) {

        if (repository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException(request.email());
        }

        Cliente cliente = mapper.toEntity(request);
        repository.save(cliente);

        return mapper.toDto(cliente);
    }

    @Override
    public ClienteResponseDto buscarPorId(UUID id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente", id));

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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente", id));

        boolean emailEmUso = repository.existsByEmail(request.email()) &&
                !cliente.getEmail().equalsIgnoreCase(request.email());

        if (emailEmUso) {
            throw new EmailJaCadastradoException(request.email());
        }

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());

        repository.save(cliente);

        return mapper.toDto(cliente);
    }

    @Override
    public void deletarCliente(UUID id) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente", id));

        repository.deleteById(id);
    }
}
