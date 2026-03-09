package com.weg.centroweg.gestaovendas.application.service.contracts;

import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {

    ClienteResponseDto criarCliente(ClienteRequestDto request);

    ClienteResponseDto buscarPorId(UUID id);

    List<ClienteResponseDto> listarClientes();

    ClienteResponseDto atualizarCliente(UUID id, ClienteRequestDto request);

    void deletarCliente(UUID id);

}
