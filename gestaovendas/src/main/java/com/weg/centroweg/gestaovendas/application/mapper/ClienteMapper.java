package com.weg.centroweg.gestaovendas.application.mapper;

import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.cliente.ClienteResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequestDto dto) {
        return new Cliente(
                dto.nome(),
                dto.email(),
                dto.telefone()
        );
    }

    public ClienteResponseDto toDto (Cliente cliente) {
        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }

}
