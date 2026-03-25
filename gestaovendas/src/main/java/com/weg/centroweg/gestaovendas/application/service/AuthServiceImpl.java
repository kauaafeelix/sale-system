package com.weg.centroweg.gestaovendas.application.service;

import com.weg.centroweg.gestaovendas.application.dto.auth.request.LoginRequest;
import com.weg.centroweg.gestaovendas.application.dto.auth.request.RegisterRequest;
import com.weg.centroweg.gestaovendas.application.dto.auth.response.AuthResponse;
import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.AuthService;
import com.weg.centroweg.gestaovendas.application.service.contracts.UsuarioService;
import com.weg.centroweg.gestaovendas.domain.entity.Usuario;
import com.weg.centroweg.gestaovendas.domain.repository.UsuarioRepository;
import com.weg.centroweg.gestaovendas.infra.exception.BusinessException;
import com.weg.centroweg.gestaovendas.infra.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;


    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                )
        );

        Usuario usuario = usuarioRepository
                .findByEmail(request.email())
                .orElseThrow();

        String token = jwtService.gerarToken(usuario);

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse registrar(RegisterRequest request) {

        UsuarioRequestDto usuarioDto = new UsuarioRequestDto(
                request.nome(),
                request.email(),
                request.senha(),
                request.role()
                );

        UsuarioResponseDto usuario = usuarioService.criarUsuario(usuarioDto);

        Usuario entity = usuarioRepository.findByEmail(usuario.email())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        String token = jwtService.gerarToken(entity);
        return new AuthResponse(token);
    }


}
