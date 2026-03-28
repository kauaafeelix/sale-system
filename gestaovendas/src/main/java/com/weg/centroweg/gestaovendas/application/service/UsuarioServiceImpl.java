package com.weg.centroweg.gestaovendas.application.service;


import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.UsuarioMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.UsuarioService;
import com.weg.centroweg.gestaovendas.domain.entity.Usuario;
import com.weg.centroweg.gestaovendas.domain.repository.UsuarioRepository;
import com.weg.centroweg.gestaovendas.infra.exception.EmailJaCadastradoException;
import com.weg.centroweg.gestaovendas.infra.exception.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper mapper;


    @Override
    public UsuarioResponseDto criarUsuario(UsuarioRequestDto request) {


        if (usuarioRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException(request.email());
        }

        Usuario usuario = mapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setDataCriacao(LocalDateTime.now());

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
            usuario.setCriadoPor(null);
        } else {
            String email = auth.getName();
            Usuario admin = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", email));
            usuario.setCriadoPor(admin);
        }

        usuarioRepository.save(usuario);
        return mapper.toDto(usuario);
    }

    @Override
    public UsuarioResponseDto buscarPorId(UUID id) {

        return usuarioRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", id));
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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", id));

        boolean emailEmUso = usuarioRepository.existsByEmail(request.email()) &&
                !usuario.getEmail().equalsIgnoreCase(request.email());

        if (emailEmUso) {
            throw new EmailJaCadastradoException(request.email());
        }

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
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", id));

        usuarioRepository.deleteById(id);
    }
}
