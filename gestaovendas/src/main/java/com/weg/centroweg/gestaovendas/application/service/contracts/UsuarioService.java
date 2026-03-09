package com.weg.centroweg.gestaovendas.application.service.contracts;

import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioResponseDto;
import com.weg.centroweg.gestaovendas.domain.entity.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {

    UsuarioResponseDto criarUsuario(UsuarioRequestDto request);

    UsuarioResponseDto buscarPorId(UUID id);

    List<UsuarioResponseDto> listarUsuarios();

    UsuarioResponseDto atualizarUsuario(UUID id, UsuarioRequestDto request);

    void deletarUsuario(UUID id);
}
