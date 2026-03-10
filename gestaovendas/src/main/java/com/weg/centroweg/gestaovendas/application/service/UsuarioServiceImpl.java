package com.weg.centroweg.gestaovendas.application.service;


import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioResponseDto;
import com.weg.centroweg.gestaovendas.application.mapper.UsuarioMapper;
import com.weg.centroweg.gestaovendas.application.service.contracts.UsuarioService;
import com.weg.centroweg.gestaovendas.domain.entity.Usuario;
import com.weg.centroweg.gestaovendas.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = mapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.senha()));

        usuarioRepository.save(usuario);
        return mapper.toDto(usuario);
    }

    @Override
    public UsuarioResponseDto buscarPorId(UUID id) {

        return usuarioRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
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
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

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
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuarioRepository.deleteById(id);
    }
}
