package com.weg.centroweg.gestaovendas.application.dto.usuario;

import com.weg.centroweg.gestaovendas.domain.entity.enums.RoleUsuario;

public record UsuarioRequestDto(
        String nome,
        String email,
        String senha,
        RoleUsuario role
) {
}
