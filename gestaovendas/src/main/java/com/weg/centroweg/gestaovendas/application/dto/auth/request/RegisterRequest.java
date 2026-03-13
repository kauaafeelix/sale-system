package com.weg.centroweg.gestaovendas.application.dto.auth.request;

import com.weg.centroweg.gestaovendas.domain.entity.enums.RoleUsuario;

public record RegisterRequest(
        String nome,
        String email,
        String senha,
        RoleUsuario role

) {
}
