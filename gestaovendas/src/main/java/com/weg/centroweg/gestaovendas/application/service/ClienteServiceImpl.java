package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    @Override
    public ClienteResponseDto criarCliente(ClienteRequestDto request) {
        return null;
    }

    @Override
    public ClienteResponseDto buscarPorId(UUID id) {
        return null;
    }

    @Override
    public List<ClienteResponseDto> listarClientes() {
        return List.of();
    }

    @Override
    public ClienteResponseDto atualizarCliente(UUID id, ClienteRequestDto request) {
        return null;
    }

    @Override
    public void deletarCliente(UUID id) {

    }
}
