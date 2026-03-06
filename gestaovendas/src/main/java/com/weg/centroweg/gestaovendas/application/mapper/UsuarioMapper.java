package com.weg.centroweg.gestaovendas.application.mapper;

import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDto dto) {

      return new Usuario(
                dto.nome(),
                dto.email(),
                dto.senha(),
                dto.role()
      );
    }

    public UsuarioResponseDto toDto (Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }

}
