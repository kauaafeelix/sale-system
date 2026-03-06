package com.weg.centroweg.gestaovendas.application.dto.usuario;

import com.weg.centroweg.gestaovendas.domain.entity.enums.RoleUsuario;

import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String email,
        RoleUsuario role
) {
}
