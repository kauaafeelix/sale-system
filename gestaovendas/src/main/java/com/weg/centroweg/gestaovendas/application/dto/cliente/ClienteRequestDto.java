package com.weg.centroweg.gestaovendas.application.dto.cliente;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(
        @NotBlank (message = "O nome do cliente é obrigatório")
        String nome,

        @Email (message = "O email do cliente deve ser válido")
        @NotBlank(message = "O email do cliente é obrigatório")
        String email,

        @NotBlank (message = "O telefone do cliente é obrigatório")
        String telefone
) {
}
