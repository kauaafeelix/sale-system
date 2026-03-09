package com.weg.centroweg.gestaovendas.application.dto.usuario;

import com.weg.centroweg.gestaovendas.domain.entity.enums.RoleUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
        RoleUsuario role
) {
}
