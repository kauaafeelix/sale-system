package com.weg.centroweg.gestaovendas.web.controller;

import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.usuario.UsuarioResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioResponseDto>save(@Valid @RequestBody UsuarioRequestDto request){

        UsuarioResponseDto response = usuarioService.criarUsuario(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable UUID id){
        UsuarioResponseDto response = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable UUID id, @Valid @RequestBody UsuarioRequestDto request) {

        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
