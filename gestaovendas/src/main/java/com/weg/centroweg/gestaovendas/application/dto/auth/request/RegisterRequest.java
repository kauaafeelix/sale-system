package com.weg.centroweg.gestaovendas.application.dto.auth.request;

public record RegisterRequest(
        String nome,
        String email,
        String senha
) {
}
