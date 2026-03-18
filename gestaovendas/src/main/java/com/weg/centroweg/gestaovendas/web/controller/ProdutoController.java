package com.weg.centroweg.gestaovendas.web.controller;

import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoRequestDto;
import com.weg.centroweg.gestaovendas.application.dto.produto.ProdutoResponseDto;
import com.weg.centroweg.gestaovendas.application.service.contracts.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProdutoResponseDto>save(
            @Valid @RequestBody ProdutoRequestDto request
    ){
        ProdutoResponseDto response = service.criarProduto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>>findAll(){
        List<ProdutoResponseDto> responses = service.listarProdutos();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> findById(@PathVariable UUID id){
        ProdutoResponseDto response = service.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ProdutoResponseDto>> findByNameContaining(@PathVariable String nome){
        List<ProdutoResponseDto> responses = service.buscarPorNome(nome);

        return ResponseEntity.ok(responses);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> update(
            @PathVariable UUID id,
            @Valid @RequestBody ProdutoRequestDto request
    ){

        ProdutoResponseDto response = service.atualizarProduto(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> delete(@PathVariable UUID id){

        service.deletarProduto(id);

        return ResponseEntity.noContent().build();
    }
}
