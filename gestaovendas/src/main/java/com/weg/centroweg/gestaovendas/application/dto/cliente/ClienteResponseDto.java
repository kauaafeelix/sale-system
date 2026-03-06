package com.weg.centroweg.gestaovendas.application.dto.cliente;

import java.util.UUID;

public record ClienteResponseDto(
        UUID id,
        String nome,
        String email,
        String telefone
) {
}
