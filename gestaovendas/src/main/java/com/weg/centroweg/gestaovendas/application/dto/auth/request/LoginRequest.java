package com.weg.centroweg.gestaovendas.application.dto.auth.request;

public record LoginRequest(
        String email,
        String senha
) {
}
