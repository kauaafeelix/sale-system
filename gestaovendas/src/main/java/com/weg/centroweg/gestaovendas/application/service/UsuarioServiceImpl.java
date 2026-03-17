package com.weg.centroweg.gestaovendas.application.service;


import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.UsuarioMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.UsuarioService;
import com.weg.centroweg.gestaovendas.domain.entity.Usuario;
import com.weg.centroweg.gestaovendas.domain.repository.UsuarioRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper mapper;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            UsuarioMapper mapper
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UsuarioResponseDto criarUsuario(UsuarioRequestDto request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Usuario admin = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Usuario usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setRole(request.role());

        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setCriadoPor(admin);

        usuarioRepository.save(usuario);

        return mapper.toDto(usuario);
    }

    @Override
    public UsuarioResponseDto buscarPorId(UUID id) {

        return usuarioRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
    }

    @Override
    public List<UsuarioResponseDto> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public UsuarioResponseDto atualizarUsuario(UUID id, UsuarioRequestDto request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        if (request.senha() != null && !request.senha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(request.senha()));
        }
        usuario.setRole(request.role());

        usuarioRepository.save(usuario);

        return mapper.toDto(usuario);
    }

    @Override
    public void deletarUsuario(UUID id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        usuarioRepository.deleteById(id);
    }
}
