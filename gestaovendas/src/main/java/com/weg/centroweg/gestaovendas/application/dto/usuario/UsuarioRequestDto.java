package com.weg.centroweg.gestaovendas.application.dto.usuario;

import com.weg.centroweg.gestaovendas.domain.entity.enums.RoleUsuario;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioRequestDto(

        @NotBlank(message = "O nome do usuário é obrigatório")
        String nome,

        @Email(message = "O email do usuário deve ser válido")
        @NotBlank (message = "O email do usuário é obrigatório")
        String email,

        @NotBlank (message = "A senha do usuário é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotNull (message = "O papel do usuário é obrigatório")
        RoleUsuario role,

        @NotNull(message = "A data de criação do usuário é obrigatória")
        @FutureOrPresent(message = "A data de criação do usuário não pode ser no passado")
        LocalDateTime dataCriacao,

        @NotNull(message = "O ID do usuário que criou este usuário é obrigatório")
        @Positive(message = "O ID do usuário que criou este usuário deve ser um número positivo")
        UUID criadoPor
) {
}
